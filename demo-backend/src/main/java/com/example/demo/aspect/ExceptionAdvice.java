package com.example.demo.aspect;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.domain.ApiResponse;
import com.example.demo.service.exception.CommandValidationException;

@RestControllerAdvice
public class ExceptionAdvice {
	final static Logger LOGGER = LogManager.getLogger(ExceptionAdvice.class);
	static AtomicInteger atom = new AtomicInteger(0);

	@ExceptionHandler(CommandValidationException.class)
	public ResponseEntity<ApiResponse<Void>> handleCommandValidationException(CommandValidationException ex) {
		long id = generateErrorId();
		ApiResponse<Void> apiResponse = new ApiResponse<>(400, String.format("%s: %d", ex.getMessage(), id), null);
		LOGGER.error("Error id:{}", id, ex);
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ApiResponse<Void>> handleResourceAccessException(ResourceAccessException ex) {
		long id = generateErrorId();
		ApiResponse<Void> apiResponse = new ApiResponse<>(408,
				String.format("Failed to connect rovers %s: %d", ex.getMessage(), id), null);
		LOGGER.error("Error id:{}", id, ex);
		return new ResponseEntity<>(apiResponse, HttpStatus.REQUEST_TIMEOUT);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ApiResponse<Void>> handleClientException(HttpClientErrorException ex,
			HttpServletResponse resp) {
		ApiResponse<Void> apiResponse = new ApiResponse<>(ex.getRawStatusCode(),
				String.format("Client Error : %s", ex.getResponseBodyAsString()), null);
		LOGGER.error("Client Error {}", ex.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
		long id = generateErrorId();
		ApiResponse<Void> apiResponse = new ApiResponse<>(500, String.format("Internal Server Error id: %d", id), null);
		LOGGER.error("Error id:{}", id, ex);
		return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private long generateErrorId() {
		long id = Long.parseLong(System.nanoTime() + "" + (atom.getAndIncrement() % 1_000));
		return id;
	}

}
