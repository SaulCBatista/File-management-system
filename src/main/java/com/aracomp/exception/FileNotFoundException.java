package com.aracomp.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	FileNotFoundException(String message) {
		super(message);
	}
	
}
