package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.RoverConfig;
import com.example.demo.service.exception.CommandValidationException;

public interface RoverService {
	String sendCommand(String commandStr) throws CommandValidationException;
	List<RoverConfig> findAll();

}
