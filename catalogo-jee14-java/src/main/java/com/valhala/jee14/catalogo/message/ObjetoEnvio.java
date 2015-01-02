package com.valhala.jee14.catalogo.message;

import java.io.Serializable;

/**
 * Objeto utilizado para encapsular o objeto enviado para a fila JMS e que tambem
 * guarda o cenario a ser utilizado.
 * @author Bruno
 *
 */
public class ObjetoEnvio implements Serializable {
	
	private static final long serialVersionUID = 4030954937587768740L;
	
	private Integer cenario;
	private Serializable conteudo;
	
	public ObjetoEnvio() {
		super();
	} // fim do metodo construtor
	
	public ObjetoEnvio(final Integer cenario, final Serializable conteudo) {
		this.cenario = cenario;
		this.conteudo = conteudo;
	} // fim do metodo construtor
	
	public Integer getCenario() {
		return cenario;
	}
	
	public Serializable getConteudo() {
		return conteudo;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ObjetoEnvio [cenario=");
		buffer.append(cenario);
		buffer.append(", conteudo=");
		buffer.append(conteudo);
		buffer.append("]");
		return buffer.toString();
	} // fim do metodo toString
	
} // fim da classe ObjetoEnvio