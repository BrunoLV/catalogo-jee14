package com.valhala.jee14.catalogo.patterns.servicelocator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class ServiceLocator {
	
	private static ServiceLocator instance = null;
	private Context context;
	
	private ServiceLocator() {
		try {
			this.context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	public Object getService(String name) {
		Object object = null;
		try {
			object = this.context.lookup(name);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public Object resolver(Object object, Class clazz) {
		return PortableRemoteObject.narrow(object, clazz);
	}
	
	public Object getServiceResolvido(String name, Class clazz) {
		Object object = this.getService(name);
		return this.resolver(object, clazz);
	}
	
}
