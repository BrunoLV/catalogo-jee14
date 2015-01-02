package com.valhala.jee14.catalogo.web.exceptions;

public class WebTierException extends Exception {

	private static final long serialVersionUID = 3243788372209053717L;
	
	public WebTierException() {
		super();
	}
	
	public WebTierException(final String message) {
		super(message);
	}
	
	public WebTierException(final Throwable cause) {
		super(cause);
	}
	
	public WebTierException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
