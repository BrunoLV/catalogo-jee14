function carregarTabela() {
	ControllerDWR.obterLivros(popularTabela);
}

function popularTabela(array) {
	var cellFuncs = [ function(data) {
		return data.codigo;
	}, function(data) {
		return data.titulo;
	}, function(data) {
		return data.numeroPaginas;
	}, function(data) {
		return data.idioma;
	}, function(data) {
		return "<input type='button' value='Editar' class='botaoFormulario' onclick='editar(" + data.codigo + ");' />";
	}, function(data) {
		return "<input type='button' value='Deletar' class='botaoFormulario' onclick='deletar(" + data.codigo + ");' />";
	} ];
	dwr.util.removeAllRows("tabelaLivro");
	dwr.util.addRows("tabelaLivro", array, cellFuncs, {
		escapeHtml : false
	});
}

function limpar() {
	dwr.util.setValue('hiddenCodigo', undefined);
	dwr.util.setValue('textTitulo', undefined);
	dwr.util.setValue('textNumeroPaginas', undefined);
	dwr.util.setValue('textIdioma', undefined);
}

function gravar() {
	var livro = {
		codigo:dwr.util.getValue('hiddenCodigo'),
		titulo:dwr.util.getValue('textTitulo'),
		numeroPaginas:dwr.util.getValue('textNumeroPaginas'),
		idioma:dwr.util.getValue('textIdioma')
	};
	
	if(livro.codigo == undefined || livro.codigo == null || livro.codigo == 0) {
		ControllerDWR.inserirLivro(livro, tratarRetornoCRUD, capturarErro);
	} else {
		ControllerDWR.editarLivro(livro, tratarRetornoCRUD, capturarErro);
	}
}

function tratarRetornoCRUD(mensagem) {
	alert(mensagem);
	limpar();
	carregarTabela();
}

function editar(codigo) {
	ControllerDWR.obterLivroPorCodigo(codigo, popularForm);
}

function popularForm(livro) {
	dwr.util.setValue('hiddenCodigo', livro.codigo);
	dwr.util.setValue('textTitulo', livro.titulo);
	dwr.util.setValue('textNumeroPaginas', livro.numeroPaginas);
	dwr.util.setValue('textIdioma', livro.idioma);
}

function deletar(codigo) {
	ControllerDWR.deletarLivro(codigo, tratarRetornoCRUD, capturarErro);
}

function capturarErro(mensagem, exception) {
	alert(mensagem);
	alert(exception);
}