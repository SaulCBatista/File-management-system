package com.aracomp.fileSystem.exception;

public class SizeUnenoughException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SizeUnenoughException(String message) {
		super(message);
	}

}