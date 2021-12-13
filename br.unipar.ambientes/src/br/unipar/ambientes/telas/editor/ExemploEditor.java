package br.unipar.ambientes.telas.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;

import br.unipar.ambientes.aplicacao.helper.MessageHelper;
import br.unipar.ambientes.modelo.Exemplo;
import br.unipar.ambientes.telas.editor.editorInput.ExemploEditorInput;

public class ExemploEditor extends AbstractEditor {
	protected DataBindingContext bindingContext;
	public static final String ID = "br.unipar.ambientes.telas.editor.ExemploEditor"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Exemplo exemplo;
	
	private Text txtTexto;

	public ExemploEditor() {
		
	}

	@Override
	protected void addComponents(Composite compositeTop) {
		Composite container = compositeTop;
		container.setLayout(new GridLayout(2, false));
		
		Label lblEtiqueta = new Label(compositeTop, SWT.NONE);
		lblEtiqueta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(lblEtiqueta, true, true);
		lblEtiqueta.setText("Etiqueta");
		
		txtTexto = new Text(compositeTop, SWT.BORDER);
		txtTexto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(txtTexto, true, true);
		
		bindingContext = initDataBindings();
	}

	@Override
	protected void salvarRegistro() {
		// TODO Auto-generated method stub
		MessageHelper.openInformation("Texto digitado: "+exemplo.getExemplo());
	}

	@Override
	protected void excluirRegistro() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// Initialize the editor part
		ExemploEditorInput exemploEditorInput = (ExemploEditorInput) input;
		this.exemplo = new Exemplo();
		setSite(site);
		setInput(exemploEditorInput);
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtTextoObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtTexto);
		IObservableValue exemploExemploObserveValue = PojoProperties.value("exemplo").observe(exemplo);
		bindingContext.bindValue(observeTextTxtTextoObserveWidget, exemploExemploObserveValue, null, null);
		//
		return bindingContext;
	}
}
