package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.modelo.Auditoria;

public interface AuditoriaEJB extends EJBObject {
	
	void inserirAuditoria(Auditoria auditoria) throws RemoteException, CatalagoException;

}
