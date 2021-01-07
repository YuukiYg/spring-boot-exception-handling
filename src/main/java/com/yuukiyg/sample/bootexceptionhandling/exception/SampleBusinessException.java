package com.yuukiyg.sample.bootexceptionhandling.exception;

public class SampleBusinessException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SampleBusinessException(String message) {
		super(message);
	}
}
