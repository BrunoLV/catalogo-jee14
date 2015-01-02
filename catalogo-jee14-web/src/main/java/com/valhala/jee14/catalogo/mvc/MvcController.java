package com.valhala.jee14.catalogo.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.valhala.jee14.catalogo.mvc.controllers.Controller;
import com.valhala.jee14.catalogo.mvc.factory.ControllerFactory;
import com.valhala.jee14.catalogo.patterns.factory.Factory;
import com.valhala.jee14.catalogo.patterns.factory.exception.FactoryException;

public class MvcController extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(MvcController.class);

	private static final long serialVersionUID = -2796765710996191775L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.tratarRequisicao(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.tratarRequisicao(req, resp);
	}
	
	private void tratarRequisicao(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getParameter("opcaoController"));
		int opcao = Integer.valueOf(req.getParameter("opcaoController")).intValue();
		Factory factory = new ControllerFactory();
		try {
			Controller controller = (Controller) factory.obterObjeto(opcao);
			LOGGER.info("Executando o controller: " + controller.getClass().getName());
			controller.executar(req, resp);
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
