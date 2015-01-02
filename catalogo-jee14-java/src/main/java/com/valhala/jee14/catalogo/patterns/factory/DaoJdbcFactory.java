package com.valhala.jee14.catalogo.patterns.factory;

import java.util.ResourceBundle;
import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;

public final class DaoJdbcFactory implements Factory {
	
	public static final int LIVRO_DAO = 1;
	public static final int AUDITORIA_DAO = 2;
	
	private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("factory");

	public Object obterObjeto() throws FactoryException {
		throw new FactoryException("Objeto padrão não definido.");
	}

	public Object obterObjeto(int opcaoDao) throws FactoryException {
		return criarObjeto(opcaoDao);
	}

	private Object criarObjeto(final int opcao) throws FactoryException {
		Object objeto = null;
		Class classe = null;
		try {
			switch (opcao) {
			case DaoJdbcFactory.LIVRO_DAO:
				classe = Class.forName(this.RESOURCE_BUNDLE.getString("livro.dao.jdbc.class"));
				break;
			case DaoJdbcFactory.AUDITORIA_DAO:
				classe = Class.forName(this.RESOURCE_BUNDLE.getString("auditoria.dao.jdbc.class"));
				break;
			default:
				throw new FactoryException("Opcao de fabricacao invalida.");
			}
			objeto = classe.newInstance();
		} catch (ClassNotFoundException e) {
			throw new FactoryException("Erro com a criacao dinamica da classe.", e);
		} catch (InstantiationException e) {
			throw new FactoryException("Erro com a instanciacao dinamica do objeto.", e);
		} catch (IllegalAccessException e) {
			throw new FactoryException("Erro de nivel de acesso de classe.", e);
		}
		return objeto;
	}

}
