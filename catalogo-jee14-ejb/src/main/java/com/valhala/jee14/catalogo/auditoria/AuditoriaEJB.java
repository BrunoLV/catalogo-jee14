package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.modelo.Auditoria;

/**
 * Interface do EJB utilizado para executar funcionalidades relacionadas a
 * auditoria dentro do sistema.
 *
 * @author Bruno
 */
public interface AuditoriaEJB extends EJBObject {

    /**
     * Metodo utilizado para definir o funcionalide de inclusão de registros de
     * auditoria.
     *
     * @param auditoria
     * @throws RemoteException
     * @throws CatalagoException
     */
    void inserirAuditoria(Auditoria auditoria) throws RemoteException, CatalagoException;

} // fim da interface AuditoriaEJB
