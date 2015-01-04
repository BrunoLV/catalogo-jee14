package com.valhala.jee14.catalogo.livros;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.sql.DataSource;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.modelo.Livro;
import com.valhala.jee14.catalogo.patterns.dao.LivroDao;
import com.valhala.jee14.catalogo.patterns.dao.exceptions.DaoException;
import com.valhala.jee14.catalogo.patterns.factory.AbstractFactory;
import com.valhala.jee14.catalogo.patterns.factory.DaoJdbcFactory;
import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;
import com.valhala.jee14.catalogo.patterns.servicelocator.ServiceLocator;

public class LivroEJBBean implements SessionBean {

    private static final long serialVersionUID = 9148264587809786280L;

    private SessionContext context;

    public void inserirLivro(Livro livro) throws CatalagoException {
        Connection connection = null;
        LivroDao dao = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            dao.inserir(livro);
        } catch (DaoException e) {
            context.setRollbackOnly();
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
    } // fim do metodo inserirLivro

    public Integer inserirLivroRetornandoCodigoGerado(Livro livro) throws CatalagoException {
        Connection connection = null;
        Integer codigoGerado = null;
        LivroDao dao = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            codigoGerado = dao.inserirRetornandoKey(livro);
        } catch (DaoException e) {
            context.setRollbackOnly();
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
        return codigoGerado;
    }

    public void atualizarDadosDoLivro(Livro livro) throws CatalagoException {
        Connection connection = null;
        LivroDao dao = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            dao.atualizar(livro);
        } catch (DaoException e) {
            context.setRollbackOnly();
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void deletarLivro(Integer codigo) throws CatalagoException {
        Connection connection = null;
        LivroDao dao = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            dao.deletar(codigo);
        } catch (DaoException e) {
            context.setRollbackOnly();
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public Livro buscarLivroPorCodigo(Integer codigo) throws CatalagoException {
        Connection connection = null;
        LivroDao dao = null;
        Livro livro = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            livro = dao.buscarPorCodigo(codigo);
        } catch (DaoException e) {
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
        return livro;
    }

    public List buscarTodosLivros() throws CatalagoException {
        Connection connection = null;
        List livros = null;
        LivroDao dao = null;
        try {
            dao = (LivroDao) AbstractFactory.obterFabricaDao().obterObjeto(DaoJdbcFactory.LIVRO_DAO);
            connection = openConnection("java:/livroDS", DataSource.class);
            dao.setConnection(connection);
            livros = dao.buscarTodos();
        } catch (DaoException e) {
            throw new CatalagoException(e.getMessage(), e);
        } catch (FactoryException e) {
            throw new CatalagoException(e);
        } finally {
            closeConnection(connection);
        }
        return livros;
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (Exception e) {

        }
    }

    private Connection openConnection(final String nomeDataSource, Class classe) throws CatalagoException {
        DataSource dataSource = (DataSource) ServiceLocator.getInstance().getServiceResolvido(nomeDataSource, classe);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new CatalagoException(e.getMessage(), e);
        }
        return connection;
    }

    public void ejbCreate() throws EJBException {

    }

    public void ejbActivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbPassivate() throws EJBException, RemoteException {
        // TODO Auto-generated method stub
    }

    public void ejbRemove() throws EJBException, RemoteException {

    }

    public void setSessionContext(SessionContext context) throws EJBException, RemoteException {
        this.context = context;
    }

}
