package com.valhala.jee14.catalogo.patterns.dao.exceptions;

public class DaoException extends Exception {
	
	private static final long serialVersionUID = 7060537078733715105L;
	
	public DaoException() {
		super();
	}
	
	public DaoException(final String message) {
		super(message);
	}
	
	public DaoException(final Throwable cause) {
		super(cause);
	}
	
	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
}
