package com.valhala.jee14.catalogo.auditoria;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.valhala.jee14.catalogo.exception.CatalagoException;
import com.valhala.jee14.catalogo.message.ObjetoEnvio;
import com.valhala.jee14.catalogo.modelo.Auditoria;
import com.valhala.jee14.catalogo.patterns.servicelocator.ServiceLocator;

public class AuditoriaMDB implements MessageDrivenBean, MessageListener {
	
	private static final long serialVersionUID = -5789891997803558342L;
	private static final Logger LOGGER = Logger.getLogger(AuditoriaMDB.class);
	
	private MessageDrivenContext context = null;
	
	public AuditoriaMDB() {
		super();
	}
	
	public void onMessage(Message message) {
		ObjectMessage mensagem = (ObjectMessage) message;
		try {
			ObjetoEnvio envio = (ObjetoEnvio) mensagem.getObject();
			Auditoria auditoria = (Auditoria) envio.getConteudo();
			AuditoriaEJBHome home = (AuditoriaEJBHome) ServiceLocator.getInstance().getServiceResolvido(AuditoriaEJBHome.JNDI_NAME, AuditoriaEJBHome.class);
			AuditoriaEJB auditoriaEJB = home.create();
			auditoriaEJB.inserirAuditoria(auditoria);
		} catch (JMSException e) {
			LOGGER.error("Ocorreu erro ao processar mensagem da fila.", e);
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu erro ao processar mensagem da fila.", e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu erro ao processar mensagem da fila.", e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu erro ao processar mensagem da fila.", e);
		}
	}

	public void ejbCreate() throws EJBException {
		
	}
	
	public void ejbRemove() throws EJBException {

	}

	public void setMessageDrivenContext(MessageDrivenContext context)
			throws EJBException {
		this.context = context;
	}

}
