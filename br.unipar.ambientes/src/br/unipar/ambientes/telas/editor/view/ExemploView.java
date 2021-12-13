package br.unipar.ambientes.telas.editor.view;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class ExemploView extends ViewPart {

	public static final String ID = "unipar.br.pav.turma.telas.view.ExemploView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table tableExemplos;

	public ExemploView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Form frmExemplos = formToolkit.createForm(container);
		formToolkit.paintBordersFor(frmExemplos);
		frmExemplos.setText("Exemplos");
		frmExemplos.getBody().setLayout(new GridLayout(1, false));
		
		TableViewer tvExemplos = new TableViewer(frmExemplos.getBody(), SWT.BORDER | SWT.FULL_SELECTION);
		tableExemplos = tvExemplos.getTable();
		tableExemplos.setLinesVisible(true);
		tableExemplos.setHeaderVisible(true);
		tableExemplos.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.paintBordersFor(tableExemplos);
		
		TableViewerColumn tvcExemplo = new TableViewerColumn(tvExemplos, SWT.NONE);
		TableColumn tblclmnExemplo = tvcExemplo.getColumn();
		tblclmnExemplo.setWidth(100);
		tblclmnExemplo.setText("Exemplo");

		createActions();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}


	@Override
	public void setFocus() {
		// Set the focus
	}

}
