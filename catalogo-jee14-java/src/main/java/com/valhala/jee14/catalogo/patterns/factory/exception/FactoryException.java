package com.valhala.jee14.catalogo.patterns.factory.exception;

public class FactoryException extends Exception {

	private static final long serialVersionUID = 573732979630174541L;

	public FactoryException() {
		super();
	}
	
	public FactoryException(final String message) {
		super(message);
	}	
	
	public FactoryException(final Throwable cause) {
		super(cause);
	}
	
	public FactoryException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
