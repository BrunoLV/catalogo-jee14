package com.valhala.jee14.catalogo.patterns.factory;

import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;

public interface Factory {
	
	Object obterObjeto() throws FactoryException;
	Object obterObjeto(final int opcaoDao) throws FactoryException;
	
}
