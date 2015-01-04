package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Interface Home para trabalhar com o EJB AuditoriaEJB.
 *
 * @author Bruno
 */
public interface AuditoriaEJBHome extends EJBHome {

    public static final String JNDI_NAME = "AuditoriaEJBBean";

    /**
     * Metodo utilizado pela API para criar um instancia de AuditoriaEJB.
     *
     * @return
     * @throws RemoteException
     * @throws CreateException
     */
    AuditoriaEJB create() throws RemoteException, CreateException;

} // fim da interface AuditoriaEJBHome
