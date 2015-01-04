package com.valhala.jee14.catalogo.mvc.factory;

import java.util.ResourceBundle;

import com.valhala.jee14.catalogo.patterns.factory.Factory;
import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;

public class ControllerFactory implements Factory {

    public static final int LIVRO_CONTROLLER = 1;
    private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("controllers");

    public Object obterObjeto() throws FactoryException {
        throw new FactoryException("Objeto padrão inexistente.");
    }

    public Object obterObjeto(int opcaoController) throws FactoryException {
        return this.criarObjeto(opcaoController);
    }

    private Object criarObjeto(final int opcao) throws FactoryException {
        Object objeto = null;
        Class classe = null;
        try {
            switch (opcao) {
                case ControllerFactory.LIVRO_CONTROLLER:
                    System.out.println(this.RESOURCE_BUNDLE.getString("livro.mvc.controller.class"));
                    classe = Class.forName(this.RESOURCE_BUNDLE.getString("livro.mvc.controller.class"));
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
