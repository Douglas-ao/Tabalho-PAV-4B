package br.unipar.ambientes.aplicacao.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.google.common.collect.Lists;

import br.unipar.ambientes.aplicacao.exception.ValidationException;

/**
 * Auxilia na validação de modo geral do {@code Object} antes de salvar ou antes de abrir alguns {@code Editor}.
 * @author Filipe Wutzke
 */
public abstract class ValidatorHelper {

	private ValidatorHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Valida o objeto informado, verificando as {@code annotation} para ver se existe algum tratamento de campos.
	 * Exemplo: 
	 * <ul>{@code @NotEmpty(message="O nome deve ser preenchido")}</br>
	 * {@code private String nomeFantasia;}</ul>
	 * Neste exemplo a exceção que seria retornada é a mensagem que está na anotação
	 *
	 * @param obj o objeto a ser validado
	 * @throws ValidationException a exceção da validação do objeto
	 */
	public static void validar(Object obj) throws ValidationException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Object>> violations = factory.getValidator().validate(obj);
		StringBuilder erro = new StringBuilder();
		List<String> mensagens = Lists.newArrayList();
		for (ConstraintViolation<Object> violation : violations) {
			erro.append(violation.getMessage()).append("\n");
			mensagens.add(violation.getMessage());
		}
		
		if(erro.length() > 0)
			throw new ValidationException(erro.toString(), mensagens);
	}
	
	/**
	 * Verifica se o objeto informado é nulo.
	 *
	 * @param obj o objeto a ser verificado
	 * @return {@code true}, se estiver {@code null}
	 */
	public static boolean isNull(Object obj){
		return obj == null;
	}
	
	/**
	 * Verifica se o objeto informado não é nulo.
	 *
	 * @param obj o objeto a ser verificado
	 * @return {@code false}, se estiver {@code null}
	 */
	public static boolean isNotNull(Object obj){
		return obj != null;
	}
	
	/**
	 * Verifica se tem conexão com a intenet
	 * @return {@code true} se estiver com conexão ativa
	 */
	public static boolean isConnected() {
		try {
			InetAddress.getByName("www.google.com");//time-a.nist.gov
			return true;
		} catch (UnknownHostException eh) {
			eh.printStackTrace();
			return false;
		}
	}
	
}
