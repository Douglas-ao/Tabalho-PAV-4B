package br.unipar.ambientes.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import br.unipar.ambientes.aplicacao.helper.EditorHelper;
import br.unipar.ambientes.telas.editor.ExemploEditor;
import br.unipar.ambientes.telas.editor.editorInput.ExemploEditorInput;

public class OpenExemploCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		EditorHelper.abrirEditor(new ExemploEditorInput(), ExemploEditor.ID);
		return null;
	}

}
