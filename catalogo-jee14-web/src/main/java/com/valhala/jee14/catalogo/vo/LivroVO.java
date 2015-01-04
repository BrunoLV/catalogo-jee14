package com.valhala.jee14.catalogo.vo;

import java.io.Serializable;

public class LivroVO implements Serializable {

    private static final long serialVersionUID = 7694345797554915403L;

    private Integer codigo = null;
    private String titulo = null;
    private Integer numeroPaginas = null;
    private String idioma = null;

    public LivroVO() {
        super();
    }

    public LivroVO(final String titulo, final Integer numeroPaginas, final String idioma) {
        super();
        this.titulo = titulo;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    public LivroVO(final Integer codigo, final String titulo, final Integer numeroPaginas, final String idioma) {
        super();
        this.codigo = codigo;
        this.titulo = titulo;
        this.numeroPaginas = numeroPaginas;
        this.idioma = idioma;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Revista [codigo=");
        buffer.append(codigo);
        buffer.append(", titulo=");
        buffer.append(titulo);
        buffer.append(", numeroPaginas=");
        buffer.append(numeroPaginas);
        buffer.append(", idioma=");
        buffer.append(idioma);
        buffer.append("]");
        return buffer.toString();
    }

}
