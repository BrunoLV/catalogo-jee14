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
import org.apache.log4j.Logger;

/**
 * Uma implementacao de AuditoriaEJB. Aqui o real comportamento das
 * funcionalidades é definido.
 *
 * @author Bruno
 */
public class AuditoriaEJBBean implements SessionBean {
    
    private static final Logger LOGGER = Logger.getLogger(AuditoriaEJBBean.class);
    
    private static final long serialVersionUID = 1247722340745016337L;
    
    private SessionContext context;

    /**
     * Implementação do metodo inserirAuditoria.
     *
     * @param auditoria
     * @throws CatalagoException
     */
    public void inserirAuditoria(Auditoria auditoria) throws CatalagoException {
        Connection connection = null;
        AuditoriaDao dao = null;
        try {
            dao = (AuditoriaDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.AUDITORIA_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            dao.inserir(auditoria);
            LOGGER.info("Auditoria inserida com sucesso.");
        } catch (DaoException e) {
            LOGGER.error("Ocorreu um erro ao inserir a auditoria.", e);
            context.setRollbackOnly();
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            LOGGER.error("Ocorreu um erro ao instancia das classes de manipulacao de banco de dados.");
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        } // fim do bloco try/catch
    } // fim do metodo inserirAuditoria

    public void ejbCreate() throws EJBException {
    }
    
    public void ejbActivate() throws EJBException, RemoteException {
    }
    
    public void ejbPassivate() throws EJBException, RemoteException {
    }
    
    public void ejbRemove() throws EJBException, RemoteException {
    }

    /**
     * Metodo utilizado para encerrar recursos de banco de dados.
     *
     * @param connection
     */
    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            } // fim do bloco if
        } catch (Exception e) {
            LOGGER.error("Ocorreu um erro ao fechar recursos.", e);
        } // fim do bloco try/catch
    } // fim do metodo closeConnection

    /**
     * Metodo utilizado para abrir recurosos de banco de dados.
     *
     * @param nomeDataSource
     * @param classe
     * @return
     * @throws CatalagoException
     */
    private Connection openConnection(final String nomeDataSource, Class classe) throws CatalagoException {
        DataSource dataSource = (DataSource) ServiceLocator.getInstance().getServiceResolvido(nomeDataSource, classe);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Ocorreu um erro ao abrir recurso de banco de dados.", e);
            throw new CatalagoException(e.getMessage(), e);
        } // fim do bloco try/catch
        return connection;
    } // fim do metodo openConnection

    public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
        this.context = context;
    } // fim do metodo setSessionContext

} // fim da classe AuditoriaEJBBean
