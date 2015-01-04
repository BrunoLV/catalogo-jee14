package com.valhala.jee14.catalogo.patterns.factory;

import java.util.ResourceBundle;

import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;

public class AbstractFactory {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("factory");

    public static final Factory obterFabricaDao() throws FactoryException {
        return AbstractFactory.produzirFabrica(AbstractFactory.RESOURCE_BUNDLE.getString("dao.jdbc.factory.class"));
    }

    private static final Factory produzirFabrica(final String fqn) throws FactoryException {
        Factory fabrica = null;
        Class classe = null;
        try {
            classe = Class.forName(fqn);
            fabrica = (Factory) classe.newInstance();
        } catch (ClassNotFoundException e) {
            throw new FactoryException("Erro com a criacao dinamica da classe.", e);
        } catch (InstantiationException e) {
            throw new FactoryException("Erro com a instanciacao dinamica do objeto.", e);
        } catch (IllegalAccessException e) {
            throw new FactoryException("Erro de nivel de acesso de classe.", e);
        }
        return fabrica;
    }

}
