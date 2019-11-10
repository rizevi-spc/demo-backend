package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ApiResponse;
import com.example.demo.domain.RoverConfig;
import com.example.demo.service.RoverService;
import com.example.demo.service.exception.CommandValidationException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RoverController {

	@Autowired
	private RoverService roverService;

	@GetMapping("/command")
	public ResponseEntity<ApiResponse<String>> sendCommand(@RequestParam("commandStr") String commandStr)
			throws CommandValidationException {
		return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Command executed successfully.",
				roverService.sendCommand(commandStr)));
	}

	@GetMapping("/fetchRoverInfo")
	public ResponseEntity<ApiResponse<List<RoverConfig>>> findAll() {
		return ResponseEntity.ok(
				new ApiResponse<>(HttpStatus.OK.value(), "Rover Info fetched successfully.", roverService.findAll()));
	}

}
