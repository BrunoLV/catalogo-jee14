package com.valhala.jee14.catalogo.patterns.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.valhala.jee14.catalogo.modelo.Auditoria;
import com.valhala.jee14.catalogo.patterns.dao.exceptions.DaoException;

public class AuditoriaDao {
	
	public static final String NOME_CLASSE = "AuditoriaDao";
	
	private static final String SQL_INSERT = "INSERT INTO tb_auditoria_simples (data_movimento, operacao) VALUES (?, ?)";
	
	private Connection connection = null;
	
	public AuditoriaDao() {
		super();
	}
	
	public AuditoriaDao(final Connection connection) {
		super();
		this.connection = connection;
	}
	
	public Integer inserirRetornandoKey(final Auditoria auditoria) throws DaoException {
		return this.inserirAuditoria(auditoria);
	} // fim do metodo inserirLivroRetornandoKey
	
	public void inserir(final Auditoria auditoria) throws DaoException {
		this.inserirAuditoria(auditoria);
	} // fim do metodo inserirLivro
	
	/**
	 * Metodo utilizada para inserir um registro de livro na tabela de banco de dados.
	 * @param livro
	 * @return
	 * @throws DaoException
	 */
	private Integer inserirAuditoria(final Auditoria auditoria) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int numeroRegistrosAfetados = 0;
		Integer key = null;
		try {
			ps = this.connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, new java.sql.Timestamp(auditoria.getDataMovimento().getTime()));
			ps.setString(2, auditoria.getMovimento());
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
	
}
