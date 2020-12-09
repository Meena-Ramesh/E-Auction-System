package com.eas.exception;

public class BidNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BidNotFoundException(String message) {
		super(message);
	}

}
