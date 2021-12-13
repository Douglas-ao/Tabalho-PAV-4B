package br.unipar.ambientes.aplicacao.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class EscPressionadoCommand extends AbstractHandler {

	private IEditorPart activeEditor;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewReference[] refs = activePage.getViewReferences();
		
		for (IViewReference iViewReference : refs) {
			if(iViewReference.equals(activePage.getActivePartReference())){
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(iViewReference.getView(false));
				return null;
			}
		}
		
		activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if(activeEditor == null)
			return null;
		
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(activeEditor, true);
		return null;
	}
	
}
