package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface AuditoriaEJBHome extends EJBHome {

	public static final String JNDI_NAME = "AuditoriaEJBBean";
	
	AuditoriaEJB create() throws RemoteException, CreateException;

}
