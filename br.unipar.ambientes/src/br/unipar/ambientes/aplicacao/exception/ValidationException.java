package br.unipar.ambientes.aplicacao.exception;

import java.util.List;

/**
 * A Classe {@code ValidationException} é uma forma de {@code Exception} que indica condições 
 * específicas para validação de objetos, onde é possível informar a mensagem da exceção que será retornada.
 * @author Filipe Wutzke
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = -6792343688787102258L;
	private String mensagem;
	private List<String> mensagens;

	public ValidationException(String mensagem, List<String> mensagens) {
		this.mensagem = mensagem;
		this.mensagens = mensagens;
	}
	
	@Override
	public String getMessage() {
		return mensagem;
	}
	
	public List<String> getMensagens() {
		return mensagens;
	}
}
