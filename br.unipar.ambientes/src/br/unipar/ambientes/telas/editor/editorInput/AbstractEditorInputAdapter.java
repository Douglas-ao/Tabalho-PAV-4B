package br.unipar.ambientes.telas.editor.editorInput;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * Classe abstrata pra não ter de implementar sempre todos os métodos chatos.
 * 
 * @author Filipe Wutzke
 */
public abstract class AbstractEditorInputAdapter implements IEditorInput {

	@SuppressWarnings({ "rawtypes"})
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "";
	}

}
