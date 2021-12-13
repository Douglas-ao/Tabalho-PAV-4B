package br.unipar.ambientes.aplicacao.enums;

public enum SexoEnum {
	
	MASCULINO("M", "Masculino"),
	FEMININO("F", "Feminino"),
	BISEXUAL("B", "Bisexual"),
	TRANSSEXUAL("T", "Transsexual");
	
	private SexoEnum(String sigla, String descricao){
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
	private String sigla;
	private String descricao;
	
	public String getSigla() {
		return sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
