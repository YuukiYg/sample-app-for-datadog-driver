package com.yuukiyg.sample.datadogapp.domain.exception;

public class MyBusinessException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MyBusinessException(String message) {
		super(message);
	}

}
