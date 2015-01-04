package com.valhala.jee14.catalogo.livros;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface LivroEJBHome extends EJBHome {

    public static final String JNDI_NAME = "LivroEJBBean";

    LivroEJB create() throws RemoteException, CreateException;

}
