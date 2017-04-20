package com.airline.exception;

@SuppressWarnings("serial")
public class ConnectionException extends Exception {
	public ConnectionException(Throwable cause) {
		super(cause);
	}
	
	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
