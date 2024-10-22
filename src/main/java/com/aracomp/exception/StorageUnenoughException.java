package com.aracomp.exception;

public class StorageUnenoughException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StorageUnenoughException(String message) {
		super(message);
	}
}
