package com.valhala.jee14.catalogo.mvc.controllers;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;

import javax.ejb.CreateException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.livros.LivroEJB;
import com.valhala.jee14.catalogo.livros.LivroEJBHome;
import com.valhala.jee14.catalogo.message.ConstantesCenario;
import com.valhala.jee14.catalogo.message.MessageSender;
import com.valhala.jee14.catalogo.message.MessageSenderException;
import com.valhala.jee14.catalogo.message.ObjetoEnvio;
import com.valhala.jee14.catalogo.modelo.Auditoria;
import com.valhala.jee14.catalogo.modelo.Livro;
import com.valhala.jee14.catalogo.patterns.servicelocator.ServiceLocator;

public class LivroController implements Controller {
	
	public void executar(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = null;
		
		try {
			Livro livro = new Livro(request.getParameter("titulo"), 
					Integer.valueOf(request.getParameter("numeroPaginas")), 
					request.getParameter("idioma"));
			
			LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
			LivroEJB livroEJB = home.create();
			Integer integer = livroEJB.inserirLivroRetornandoCodigoGerado(livro);
			System.out.println(integer);
			
			request.setAttribute("mensagem", "Sucesso");
			
			Auditoria auditoria = new Auditoria(new Date(), "INSERT");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				e.printStackTrace();
			}
			
			dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (CatalagoException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

}
