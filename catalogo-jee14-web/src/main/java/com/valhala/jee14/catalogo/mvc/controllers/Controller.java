package com.valhala.jee14.catalogo.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

    void executar(HttpServletRequest request, HttpServletResponse response);

}
