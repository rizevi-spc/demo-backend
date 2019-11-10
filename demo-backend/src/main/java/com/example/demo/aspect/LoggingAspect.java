package com.example.demo.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.demo.aspect.annotation.Logging;

@Aspect
@Component
public class LoggingAspect {
	final static Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	@AfterReturning(value = "@annotation(logging)", returning = "returnObj")
	public void logging(JoinPoint joinPoint, Object returnObj, Logging logging) {
		LOGGER.info("Method executed successfully {}", joinPoint.getSignature().getName());

	}

	@AfterThrowing(value = "@annotation(logging)", throwing = "ex")
	public void logging(JoinPoint joinPoint, Throwable ex, Logging logging) {
		LOGGER.error("Method unsuccessful {} exception: {}", joinPoint.getSignature().getName(), ex.toString());

	}
}
