package com.airline.exception;

@SuppressWarnings("serial")
public class SystemException extends Exception {
	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
