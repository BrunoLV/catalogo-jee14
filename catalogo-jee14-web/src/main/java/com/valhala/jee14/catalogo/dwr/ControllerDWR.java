package com.valhala.jee14.catalogo.dwr;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

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
import com.valhala.jee14.catalogo.vo.LivroVO;
import com.valhala.jee14.catalogo.web.exceptions.WebTierException;

public class ControllerDWR {
	
	private static final Logger LOGGER = Logger.getLogger(ControllerDWR.class);
	
	/**
	 * Metodo utilizado para executar a funcionalidade de consulta de livros.
	 * @return
	 * @throws WebTierException 
	 */
	public LivroVO[] obterLivros() throws WebTierException {
		LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
		LivroEJB ejb;
		List livrosCadastrados = null;
		LivroVO[] livros = null;
		try {
			ejb = home.create();
			livrosCadastrados = ejb.buscarTodosLivros();
			livros = new LivroVO[livrosCadastrados.size()];
			int count = 0;
			for (Iterator iterator = livrosCadastrados.iterator(); iterator.hasNext();) {
				LivroVO livroVO = new LivroVO();
				Livro livro = (Livro) iterator.next();
				BeanUtils.copyProperties(livroVO, livro);
				livros[count] = livroVO;
				count++;
			} // fim do bloco for
			
			Auditoria auditoria = new Auditoria(new Date(), "SELECT");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				LOGGER.warn("Ocorreu erro ao enviar mensagem para fila de auditoria.", e);
			}
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} // fim do bloco try/catch
		return livros;
	} // fim do metodo obterLivros
	
	/**
	 * Metodo utilizado para buscar livros utilizado o codigo como chave.
	 * @param codigo
	 * @return
	 * @throws WebTierException 
	 */
	public LivroVO obterLivroPorCodigo(final Integer codigo) throws WebTierException {
		LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
		LivroEJB ejb;
		Livro livro = null;
		LivroVO livroVO = null;
		try {
			ejb = home.create();
			livro = ejb.buscarLivroPorCodigo(codigo);
			livroVO = new LivroVO();
			BeanUtils.copyProperties(livroVO, livro);
			
			Auditoria auditoria = new Auditoria(new Date(), "SELECT");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				LOGGER.warn("Ocorreu erro ao enviar mensagem para fila de auditoria.", e);
			}
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} // fim do bloco try/catch
		return livroVO;
	} // fim do metodo obterLivroPorCodigo

	/**
	 * Metodo utilizado para inserir livros no sistema.
	 * @param livroVO
	 * @return
	 * @throws WebTierException 
	 */
	public String inserirLivro(final LivroVO livroVO) throws WebTierException {
		String mensagem = null;
		LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
		LivroEJB ejb;
		try {
			ejb = home.create();
			Livro livro = new Livro();
			BeanUtils.copyProperties(livro, livroVO);
			ejb.inserirLivro(livro);
			mensagem = "Livro inserido com sucesso!!!!";
			
			Auditoria auditoria = new Auditoria(new Date(), "INSERT");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				LOGGER.warn("Ocorreu erro ao enviar mensagem para fila de auditoria.", e);
			}
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} // fim do try/catch
		return mensagem;
	} // fim do metodo inserirLivro
	
	/**
	 * Metodo utilizado para atualizar livros no sistema.
	 * @param livroVO
	 * @return
	 * @throws WebTierException
	 */
	public String editarLivro(final LivroVO livroVO) throws WebTierException {
		String mensagem = null;
		LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
		LivroEJB ejb;
		try {
			ejb = home.create();
			Livro livro = new Livro();
			BeanUtils.copyProperties(livro, livroVO);
			ejb.atualizarDadosDoLivro(livro);
			mensagem = "Livro atualizado com sucesso!!!!";
			
			Auditoria auditoria = new Auditoria(new Date(), "UPDATE");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				LOGGER.warn("Ocorreu erro ao enviar mensagem para fila de auditoria.", e);
			}
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} // fim do bloco try/catch
		return mensagem;
	} // fim do editarLivro
	
	/**
	 * Metodo utilizado para remover livros do sistema.
	 * @param codigo
	 * @return
	 * @throws WebTierException
	 */
	public String deletarLivro(final Integer codigo) throws WebTierException {
		String mensagem = null;
		LivroEJBHome home = (LivroEJBHome) ServiceLocator.getInstance().getServiceResolvido(LivroEJBHome.JNDI_NAME, LivroEJBHome.class);
		LivroEJB ejb;
		try {
			ejb = home.create();
			ejb.deletarLivro(codigo);
			mensagem = "Livro removido com sucesso";
			
			Auditoria auditoria = new Auditoria(new Date(), "DELETE");
			ObjetoEnvio envio = new ObjetoEnvio(Integer.valueOf(ConstantesCenario.CENARIO_AUDITORIA), auditoria);
		
			try {
				new MessageSender().enviarMensagem(envio);
			} catch (MessageSenderException e) {
				LOGGER.warn("Ocorreu erro ao enviar mensagem para fila de auditoria.", e);
			}
		} catch (RemoteException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CreateException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} catch (CatalagoException e) {
			LOGGER.error("Ocorreu na execucao do metodo.", e);
			throw new WebTierException(e);
		} // fim do bloco try/catch
		return mensagem;
	} // fim do metodo deletarLivro

} // fim da classe ControllerDWR