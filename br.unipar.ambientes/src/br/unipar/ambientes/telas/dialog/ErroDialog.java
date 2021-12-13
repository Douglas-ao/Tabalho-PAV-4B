package br.unipar.ambientes.telas.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ResourceManager;

import br.unipar.ambientes.aplicacao.helper.ShellHelper;

/**
 * O {@code Dialog} genérico para informar os erros ocorridos.
 * Deverá ser informado o erro e a mensagem do erro para construir o dialog
 * @author Filipe Wutzke
 */
public class ErroDialog extends TitleAreaDialog {
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private String erros;
	private String msg;

	/**
	 * Cria o dialog com os erros informados.
	 * @param erros os erros a serem mostrados
	 */
	public ErroDialog(String erros) {
		super(ShellHelper.getActiveShell());
		setShellStyle(SWT.RESIZE | SWT.APPLICATION_MODAL);
		this.erros = erros;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		if(msg != null)
			setTitle(msg);
		else
			setTitle("Não foi possível salvar o registro");

		setErrorMessage("Os erros a seguir impedem a execução");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		formToolkit.adapt(container);

		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		formToolkit.adapt(scrolledComposite);

		Text txtErros = new Text(scrolledComposite, SWT.WRAP | SWT.MULTI);
		txtErros.setEditable(false);
		txtErros.setText(erros);
		formToolkit.adapt(txtErros, true, true);
		scrolledComposite.setContent(txtErros);
		scrolledComposite.setMinSize(txtErros.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblSeparator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblSeparator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		formToolkit.adapt(lblSeparator, true, true);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, 
				true).setImage(ResourceManager.getPluginImage("br.com.germantech.ecf", "assets/funcoes/check16.png"));
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Unipar");
		super.configureShell(newShell);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 280);
	}

	public ErroDialog setMensagem(String msg){
		this.msg = msg;
		return this;
	}
}
