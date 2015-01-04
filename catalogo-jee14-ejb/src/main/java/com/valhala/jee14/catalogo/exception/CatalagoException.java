package com.valhala.jee14.catalogo.exception;

/**
 * Exception customizada para encapsular os erros disparados na camada EJB do
 * sistema.
 *
 * @author Bruno
 */
public class CatalagoException extends Exception {

    private static final long serialVersionUID = -9144158355143984744L;

    public CatalagoException() {
        super();
    }

    public CatalagoException(final String message) {
        super(message);
    }

    public CatalagoException(final Throwable cause) {
        super(cause);
    }

    public CatalagoException(final String message, final Throwable cause) {
        super(message, cause);
    }

} // fim da classe CatalogoException
