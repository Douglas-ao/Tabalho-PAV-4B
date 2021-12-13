package br.unipar.ambientes.controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.unipar.ambientes.aplicacao.enums.ClassificacaoEnum;

/**
 * Classe de controle do IMC
 * <p>
 * IMC é a sigla para Índice de Massa Corpórea, parâmetro adotado pela Organização Mundial de Saúde para calcular o peso ideal de cada pessoa. 
 * O índice é calculado da seguinte maneira: divide-se o peso do paciente pela sua altura elevada ao quadrado. 
 * Diz-se que o indivíduo tem peso normal quando o resultado do IMC está entre 18,5 e 24,9.
 * <p>
 * 
 * <table border="1">
 * <tr><th>IMC					</th><th>CLASSIFICAÇÃO		</th><th>OBESIDADE (grau)</th></tr>
 * <tr><td>MENOR QUE 18,5		</td><td>MAGREZA			</td><td>0</td></tr>
 * <tr><td>ENTRE 18,5 E 24,9	</td><td>NORMAL				</td><td>0</td></tr>
 * <tr><td>ENTRE 25,0 E 29,9	</td><td>SOBREPESO			</td><td>I</td></tr>
 * <tr><td>ENTRE 30,0 E 39,9	</td><td>OBESIDADE			</td><td>II</td></tr>
 * <tr><td>MAIOR QUE 40,0		</td><td>OBESIDADE GRAVE	</td><td>III</td></tr>
 * </table>
 * <p>
 * Para uso neste controller utilize a fórmula abaixo:
 * <ul>IMC = PESO / (ALTURA * ALTURA). Utilize as unidades de medidas Kg e Metros.</ul>
 * 
 * @author Prof. Filipe Wutzke
 *
 */
public class IndiceMassaCorporalController { 
	
	/*
	 * IMC é a sigla para Índice de Massa Corpórea, parâmetro adotado pela Organização Mundial de Saúde para calcular o peso ideal de cada pessoa. 
	 * O índice é calculado da seguinte maneira: divide-se o peso do paciente pela sua altura elevada ao quadrado. 
	 * Diz-se que o indivíduo tem peso normal quando o resultado do IMC está entre 18,5 e 24,9.
	 * 
	 * IMC					CLASSIFICAÇÃO 	OBESIDADE (grau)
	 * -----------------------------------------------------
	 * MENOR QUE 18,5		MAGREZA				0
	 * ENTRE 18,5 E 24,9	NORMAL				0
	 * ENTRE 25,0 E 29,9	SOBREPESO			I
	 * ENTRE 30,0 E 39,9	OBESIDADE			II
	 * MAIOR QUE 40,0		OBESIDADE GRAVE		III
	 * -----------------------------------------------------
	 * 
	 * FÓRMULA
	 * ------------------------------
	 * IMC = PESO / (ALTURA * ALTURA)
	 * 
	 */
	
	public ClassificacaoEnum obterClassificacaoImc(String pesoStr, String alturaStr) {
		BigDecimal altura = new BigDecimal(alturaStr); 
		BigDecimal peso = new BigDecimal(pesoStr);
		
		BigDecimal imcCalculado = calcularImc(altura, peso);
		
		//ENCONTRAR A CLASSIFICAÇÃO CORRETA
		// MAGREZA
		if(imcCalculado.compareTo(new BigDecimal("18.5")) <= 0) {
			return ClassificacaoEnum.MAGREZA;
		} 
		
		if(imcCalculado.compareTo(new BigDecimal("18.5")) > 0 && 
				imcCalculado.compareTo(new BigDecimal("24.99")) <= 0) {
			return ClassificacaoEnum.NORMAL;
		}
		
		if(imcCalculado.compareTo(new BigDecimal("25.00")) >= 0 && 
				imcCalculado.compareTo(new BigDecimal("29.99")) <= 0) {
			return ClassificacaoEnum.SOBREPESO;
		}
		
		if(imcCalculado.compareTo(new BigDecimal("30.00")) >= 0 && 
				imcCalculado.compareTo(new BigDecimal("39.99")) <= 0) {
			return ClassificacaoEnum.OBESIDADE_II;
		}
		
		if(imcCalculado.compareTo(new BigDecimal("40.00")) >= 0 ) {
			return ClassificacaoEnum.OBESIDADE_III;
		}
		
		return ClassificacaoEnum.OBESIDADE_III;
	}
	
	public BigDecimal calcularImc(BigDecimal altura, BigDecimal peso) {
		BigDecimal alturaCalculo = altura.multiply(altura);
		return peso.divide(alturaCalculo, 2, RoundingMode.HALF_UP);
	}
	
}
