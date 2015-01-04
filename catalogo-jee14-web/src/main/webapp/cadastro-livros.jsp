<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <fmt:message key="message.titulo" />
        </title>
        <link href='${pageContext.request.contextPath}/css/stylesheet.css'
              rel="stylesheet" />
        <script type="text/javascript"
        src='${pageContext.request.contextPath}/dwr/interface/ControllerDWR.js'></script>
        <script type="text/javascript"
        src='${pageContext.request.contextPath}/dwr/engine.js'></script>
        <script type="text/javascript"
        src='${pageContext.request.contextPath}/dwr/util.js'></script>
        <script type="text/javascript"
        src='${pageContext.request.contextPath}/js/cadastro-livro.js'></script>
    </head>
    <body onload="carregarTabela();">
        <h1><fmt:message key="message.titulo" /></h1>
        <form action="mvc" method="post">
            <input type="hidden" id="hiddenOpcaoController"
                   name="hiddenController" value="1" /> <input type="hidden"
                   id="hiddenCodigo" name="hiddenCodigo" />
            <table>
                <tr>
                    <td><fmt:message key="message.form.titulo" /></td>
                    <td><input type="text" class="campoFormulario" id="textTitulo"
                               name="textTitulo" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="message.form.numeroPaginas" /></td>
                    <td><input type="text" class="campoFormulario"
                               id="textNumeroPaginas" name="textNumeroPaginas" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="message.form.idioma" /></td>
                    <td><input type="text" class="campoFormulario" id="textIdioma"
                               name="textIdioma" /></td>
                </tr>
            </table>

            <br /> <input type="button" class="botaoFormulario" value='<fmt:message key="message.button.gravar" />'
                          onclick="gravar();" />
            <input type="button" class="botaoFormulario" value='<fmt:message key="message.button.limpar" />'
                   onclick="limpar();" />

            <br /> <br />

            <table>
                <tbody id="cabecalhoTabela">
                    <tr>
                        <th><fmt:message key="message.table.codigo" /></th>
                        <th><fmt:message key="message.table.titulo" /></th>
                        <th><fmt:message key="message.table.numeroPaginas" /></th>
                        <th><fmt:message key="message.table.idioma" /></th>
                        <th></th>
                        <th></th>
                    </tr>
                </tbody>
                <tbody id="tabelaLivro">
                </tbody>
            </table>

        </form>
    </body>
</html>
