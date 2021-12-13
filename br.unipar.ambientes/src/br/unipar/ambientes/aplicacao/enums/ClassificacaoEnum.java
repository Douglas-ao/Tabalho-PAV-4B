package br.unipar.ambientes.aplicacao.enums;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;

public enum ClassificacaoEnum {
	
	MAGREZA ("Magreza", SWTResourceManager.getColor(SWT.COLOR_DARK_RED)),
	NORMAL ("Normal", SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN)),
	SOBREPESO ("Sobrepeso", SWTResourceManager.getColor(SWT.COLOR_DARK_YELLOW)),
	OBESIDADE_II ("Obesidade II", SWTResourceManager.getColor(SWT.COLOR_DARK_RED)),
	OBESIDADE_III ("Obesidade III", SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
	
	private String descricao;
	private Color color;

	private ClassificacaoEnum(String descricao, Color color) {
		this.descricao = descricao;
		this.color = color;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Color getColor() {
		return color;
	}
}
