package com.joh.lhms.exception;

public class ItemNotAvaiableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ItemNotAvaiableException(String message) {
		super(message);
	}

}