package com.valhala.jee14.catalogo.message;

/**
 * Exception que encapsula erros disparados pela API de mensagem do sistema.
 * @author Bruno
 *
 */
public class MessageSenderException extends Exception {
	
	private static final long serialVersionUID = -5630434690364782672L;

	public MessageSenderException() {
		super();
	}
	
	public MessageSenderException(final String message) {
		super(message);
	}
	
	public MessageSenderException(final Throwable cause) {
		super(cause);
	}
	
	public MessageSenderException(final String message, final Throwable cause) {
		super(message, cause);
	}

} // fim da classe MessageSenderException