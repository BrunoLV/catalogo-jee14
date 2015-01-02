package com.valhala.jee14.catalogo.message;

import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.log4j.Logger;

import com.valhala.jee14.catalogo.patterns.servicelocator.ServiceLocator;

/**
 * Classe utilizada expor a funcionalidade de envio de mensagens para filas JMS
 * @author Bruno
 *
 */
public class MessageSender {
	
	private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("message");
	
	private static final Logger LOGGER = Logger.getLogger(MessageSender.class);
	
	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private ObjectMessage msg;
	
	/**
	 * Metodo utilizado para enviar mensagens para filas JMS
	 * @param envio
	 * @throws MessageSenderException
	 */
	public final void enviarMensagem(final ObjetoEnvio envio) throws MessageSenderException {
		String qcfName = null;
		String queueName = null;
		int cenario = envio.getCenario().intValue();
		switch (cenario) {
		case ConstantesCenario.CENARIO_AUDITORIA:
			qcfName = RESOURCE_BUNDLE.getString("jms.auditoria.qcf.name");
			queueName = RESOURCE_BUNDLE.getString("jms.auditoria.queue.name");
			break;
		default:
			break;
		} // fim do bloco switch
		enviar(qcfName, queueName, envio);
	} // fim do metodo enviarMensagem
	
	/*
	 * Metodo utilizado para executar o envio da mensagem
	 */
	private final void enviar(final String qcfName, final String queueName, final ObjetoEnvio envio) throws MessageSenderException {
		try {
			qconFactory = (QueueConnectionFactory) ServiceLocator.getInstance().getService(qcfName);
			qcon = qconFactory.createQueueConnection();
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ServiceLocator.getInstance().getService(queueName);
			qsender = qsession.createSender(queue);
			msg = qsession.createObjectMessage(envio);
			qcon.start();
			qsender.send(msg);
		} catch (JMSException e) {
			LOGGER.error("Ocorreu um erro ao enviar a mensagem.", e);
			throw new MessageSenderException(e);
		} finally {
		    try {
		    	if(qsender != null) {
		    		qsender.close();
		    	} // fim do bloco if
			    if(qsession != null) {
			    	qsession.close();
			    } // fim do bloco if
			    if (qcon != null) {
					qcon.close();
				} // fim do bloco if
			} catch (JMSException e) {
				LOGGER.error("Rolou erro ao fechar os recursos.", e);
			} // fim do bloco try/catch
		} // fim do bloco try/catch
	} // fim do metodo enviar

} // fim da classe MessageSender