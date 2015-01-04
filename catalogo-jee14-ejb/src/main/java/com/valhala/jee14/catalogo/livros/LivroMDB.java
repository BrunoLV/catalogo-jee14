package com.valhala.jee14.catalogo.livros;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class LivroMDB implements MessageDrivenBean, MessageListener {

    private static final long serialVersionUID = 8518867766476822946L;

    public LivroMDB() {
        super();
    }

    public void onMessage(Message message) {
        System.out.println("Ta indo....");
        System.out.println(message);
        ObjectMessage objeto = (ObjectMessage) message;
        try {
            System.out.println(objeto.getObject());
        } catch (JMSException e) {
        }
    }

    public void ejbCreate() throws EJBException {

    }

    public void ejbRemove() throws EJBException {

    }

    public void setMessageDrivenContext(MessageDrivenContext context) throws EJBException {
    }

}
