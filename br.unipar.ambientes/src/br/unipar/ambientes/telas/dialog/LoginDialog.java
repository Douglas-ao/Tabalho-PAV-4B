package br.unipar.ambientes.telas.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.unipar.ambientes.controlador.LoginController;

public class LoginDialog extends TitleAreaDialog {
	
	private LoginController controller = new LoginController();
	
	private Text txtUsuario;
	private Text txtSenha;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public LoginDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(2, false));
		
		setTitle("Login");
		setMessage("Informe o usuário e senha para efetuar o login");
		
		//AQUI DENTRO É ONDE A BRINCADEIRA ACONTECE...
		
		//COLUNA 1
		Label lblUsuario = new Label(container, SWT.NONE);
		lblUsuario.setText("Usuário");
		lblUsuario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		//COLUNA 2
		txtUsuario = new Text(container, SWT.BORDER);
		txtUsuario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//COLUNA 1
		Label lblSenha = new Label(container, SWT.NONE);
		lblSenha.setText("Senha");
		lblSenha.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		//COLUNA 2
		txtSenha = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtSenha.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//....

		return area;
	}
	
	@Override
	protected void okPressed() {
		// Ação do botão OK
		String usuario = txtUsuario.getText();
		String senha = txtSenha.getText();
		
		//O controller que vai fazer..
		if(!controller.efetuarLogin(usuario, senha)) {
			return;
		}
		
		super.okPressed();
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
	
	public static Shell getActiveShell(){
		return Display.getDefault().getActiveShell();
	}

}
