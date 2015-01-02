package com.valhala.jee14.catalogo.patterns.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.valhala.jee14.catalogo.modelo.Livro;
import com.valhala.jee14.catalogo.patterns.dao.exceptions.DaoException;

public class LivroDao {
	
	public static final String NOME_CLASSE = "LivroDao";
	
	private static final String SQL_INSERT = "INSERT INTO tb_livro (titulo, numero_paginas, idioma) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE tb_livro SET titulo = ?, numero_paginas = ?, idioma = ? WHERE codigo = ?";
	private static final String SQL_DELETE = "DELETE FROM tb_livro WHERE codigo = ?";
	private static final String SQL_SELECT_CODIGO = "SELECT * FROM tb_livro WHERE codigo = ?";
	private static final String SQL_SELECT_TODOS = "SELECT * FROM tb_livro";
	
	private Connection connection = null;
	
	public LivroDao() {
		super();
	}
	
	public LivroDao(final Connection connection) {
		super();
		this.connection = connection;
	}
	
	public Integer inserirRetornandoKey(final Livro livro) throws DaoException {
		return this.inserirLivro(livro);
	} // fim do metodo inserirLivroRetornandoKey
	
	public void inserir(final Livro livro) throws DaoException {
		this.inserirLivro(livro);
	} // fim do metodo inserirLivro
	
	/**
	 * Metodo utilizada para inserir um registro de livro na tabela de banco de dados.
	 * @param livro
	 * @return
	 * @throws DaoException
	 */
	private Integer inserirLivro(final Livro livro) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int numeroRegistrosAfetados = 0;
		Integer key = null;
		try {
			ps = this.connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, livro.getTitulo());
			ps.setInt(2, livro.getNumeroPaginas().intValue());
			ps.setString(3, livro.getIdioma());
			numeroRegistrosAfetados = ps.executeUpdate();
			if (numeroRegistrosAfetados < 1) {
				throw new DaoException("Ocorreu algum problema na operação INSERIR. Contate o administrador da aplicação.");
			} // fim do bloco if
			rs = ps.getGeneratedKeys();
			while (rs.next()) {
				key = Integer.valueOf(rs.getInt(1));
			} // fim do bloco try/catch
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			encerrarRecurso(null, ps, rs);
		} // fim do bloco try/catch
		return key;
	} // fim do metodo inserir
	
	/**
	 * Metodo utilizado para atualizar um registro de livro na tabela de banco de dados.
	 * @param livro
	 * @throws DaoException
	 */
	public void atualizar(final Livro livro) throws DaoException {
		PreparedStatement ps = null;
		int numeroRegistrosAfetados = 0;
		try {
			ps = this.connection.prepareStatement(SQL_UPDATE);
			ps.setString(1, livro.getTitulo());
			ps.setInt(2, livro.getNumeroPaginas().intValue());
			ps.setString(3, livro.getIdioma());
			ps.setInt(4, livro.getCodigo().intValue());
			numeroRegistrosAfetados = ps.executeUpdate();
			if (numeroRegistrosAfetados < 1) {
				throw new DaoException("Ocorreu algum problema na operação ATUALIZAR. Contate o administrador da aplicação.");
			} // fim do bloco if
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			encerrarRecurso(null, ps, null);
		} // fim do bloco try/catch
	} // fim do metodo atualizar
	
	/**
	 * Metodo utilizado para deletar um registro de livro da tabela de banco de dados.
	 * @param codigo
	 * @throws DaoException
	 */
	public void deletar(final Integer codigo) throws DaoException {
		PreparedStatement ps = null;
		int numeroRegistrosAfetados = 0;
		try {
			ps = this.connection.prepareStatement(SQL_DELETE);
			ps.setInt(1, codigo.intValue());
			numeroRegistrosAfetados = ps.executeUpdate();
			if (numeroRegistrosAfetados < 1) {
				throw new DaoException("Ocorreu algum problema na operação DELETAR. Contate o administrador da aplicação.");
			} // fim do bloco if
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			encerrarRecurso(null, ps, null);
		} // fim do bloco try/catch
	} // fim do metodo deletar
	
	/**
	 * Metodo utilizado para buscar um registro de livro na tabela de banco de dados utilizando o codigo.
	 * @param codigo
	 * @return
	 * @throws DaoException
	 */
	public Livro buscarPorCodigo(final Integer codigo) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Livro livro = null;
		try {
			ps = this.connection.prepareStatement(SQL_SELECT_CODIGO);
			ps.setInt(1, codigo.intValue());
			rs = ps.executeQuery();
			while (rs.next()) {
				livro = gerarLivroAPartirDeResultSet(rs);
			} // fim do bloco while
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			encerrarRecurso(null, ps, rs);
		} // fim do bloco try/catch
		return livro;
	} // fim do metodo buscarPorCodigo
	
	/**
	 * Metodo utilizado para buscar todos registro de livro na tabela de banco de dados.
	 * @return
	 * @throws DaoException
	 */
	public List buscarTodos() throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		try {
			ps = this.connection.prepareStatement(SQL_SELECT_TODOS);
			rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(gerarLivroAPartirDeResultSet(rs));
			} // fim do bloco while
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			encerrarRecurso(null, ps, rs);
		} // fim do bloco buscarTodos
		return lista;
	} // fim do metodo buscarTodos

	private Livro gerarLivroAPartirDeResultSet(final ResultSet rs) throws SQLException {
		Livro livro;
		livro = new Livro(Integer.valueOf(rs.getInt(1)), rs.getString(2), Integer.valueOf(rs.getInt(3)), rs.getString(4));
		return livro;
	} // fim do metodo gerarLivroAPartirDeResultSet
	
	private void encerrarRecurso(final Connection connection, final PreparedStatement ps, final ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				ps.close();
			}
		} catch (SQLException e) {
			
		}
	} // fim do metodo encerrarRecurso
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
} // fim da classe LivroDao