package com.valhala.jee14.catalogo.livros;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBObject;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.modelo.Livro;

public interface LivroEJB extends EJBObject {

    void inserirLivro(Livro livro) throws RemoteException, CatalagoException;

    Integer inserirLivroRetornandoCodigoGerado(Livro livro) throws RemoteException, CatalagoException;

    void atualizarDadosDoLivro(Livro livro) throws RemoteException, CatalagoException;

    void deletarLivro(Integer codigo) throws RemoteException, CatalagoException;

    Livro buscarLivroPorCodigo(Integer codigo) throws RemoteException, CatalagoException;

    List buscarTodosLivros() throws RemoteException, CatalagoException;

}
