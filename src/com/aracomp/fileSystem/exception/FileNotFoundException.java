package com.aracomp.fileSystem.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileNotFoundException() {
		super("File not found");
	}
	
}
