package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.sql.DataSource;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.modelo.Auditoria;
import com.valhala.jee14.catalogo.patterns.dao.AuditoriaDao;
import com.valhala.jee14.catalogo.patterns.dao.exceptions.DaoException;
import com.valhala.jee14.catalogo.patterns.factory.AbstractFactory;
import com.valhala.jee14.catalogo.patterns.factory.DaoJdbcFactory;
import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;
import com.valhala.jee14.catalogo.patterns.servicelocator.ServiceLocator;

public class AuditoriaEJBBean implements SessionBean {

	private static final long serialVersionUID = 1247722340745016337L;

	private SessionContext context;
	
	public void inserirAuditoria(Auditoria auditoria) throws CatalagoException {
		Connection connection = null;
		AuditoriaDao dao = null;
		try {
			dao = (AuditoriaDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.AUDITORIA_DAO);
			connection = openConnection("java:/livroDS", DataSource.class);
			dao.setConnection(connection);
			dao.inserir(auditoria);
		} catch (DaoException e) {
			context.setRollbackOnly();
			throw new CatalagoException(e.getMessage(), e);
		} catch (FactoryException e) {
			throw new CatalagoException(e);
		} finally {
			closeConnection(connection);
		}	
	}
	
	public void ejbCreate() throws EJBException {
		// TODO Auto-generated method stub
	}
	
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	private final void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}	
		} catch (Exception e) {
			
		}
	}
	
	private final Connection openConnection(final String nomeDataSource, Class classe) throws CatalagoException {
		DataSource dataSource = (DataSource) ServiceLocator.getInstance().getServiceResolvido(nomeDataSource, classe);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new CatalagoException(e.getMessage(), e);
		}
		return connection;
	}


	public void setSessionContext(SessionContext arg0) throws EJBException,
			RemoteException {
		this.context = context;
	}

}
