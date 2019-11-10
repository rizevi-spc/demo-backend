package com.example.demo.service.exception;

public class CommandValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1900753033701398657L;

	public CommandValidationException(String message){
		super(message);
	}
}
