package br.unipar.ambientes.aplicacao.enums;

public enum EstadoCivilEnum {

	SOLTEIRO ("Solteiro(a)"),
	CASADO ("Casado(a)"),
	DIVORCIADO ("Divorciado(a)"),
	VIUVO ("Viuvo(a)"),
	UNIAO_ESTAVEL("União Estável");
	
	private String descricao;

	private EstadoCivilEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
