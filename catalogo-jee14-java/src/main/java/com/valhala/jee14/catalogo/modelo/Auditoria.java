package com.valhala.jee14.catalogo.modelo;

import java.io.Serializable;
import java.util.Date;

public class Auditoria implements Serializable {
	
	private static final long serialVersionUID = 2856530462244659833L;
	
	private Integer codigo;
	private Date dataMovimento;
	private String movimento;
	
	public Auditoria() {
		super();
	}

	public Auditoria(Integer codigo, Date dataMovimento, String movimento) {
		super();
		this.codigo = codigo;
		this.dataMovimento = dataMovimento;
		this.movimento = movimento;
	}

	public Auditoria(Date dataMovimento, String movimento) {
		super();
		this.dataMovimento = dataMovimento;
		this.movimento = movimento;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getMovimento() {
		return movimento;
	}

	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Auditoria [codigo=");
		buffer.append(codigo);
		buffer.append(", dataMovimento=");
		buffer.append(dataMovimento);
		buffer.append(", movimento=");
		buffer.append(movimento);
		buffer.append("]");
		return buffer.toString();
	}

}
