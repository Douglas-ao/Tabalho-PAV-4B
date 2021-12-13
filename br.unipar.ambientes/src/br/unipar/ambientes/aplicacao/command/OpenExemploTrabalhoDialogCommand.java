package br.unipar.ambientes.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import br.unipar.ambientes.aplicacao.helper.ShellHelper;
import br.unipar.ambientes.telas.dialog.ExemploTrabalhoDialog;

public class OpenExemploTrabalhoDialogCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ExemploTrabalhoDialog dialog = new ExemploTrabalhoDialog(ShellHelper.getActiveShell());
		dialog.open();
		return null;
	}

}
