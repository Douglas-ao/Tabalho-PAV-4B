package br.unipar.ambientes.controlador;

import org.eclipse.jface.dialogs.MessageDialog;

import br.unipar.ambientes.modelo.Usuario;
import br.unipar.ambientes.telas.dialog.LoginDialog;

public class LoginController {
	
	private Usuario usuario;
	
	public LoginController() {
		super();
		initUsuarioAdministrador();
	}
	
	private void initUsuarioAdministrador() {
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Administrador");
		usuario.setLogin("admin");
		usuario.setSenha("123456");
	}
	
	public boolean efetuarLogin(String usuario, String senha) {
		//VALIDAR
		if(!this.usuario.getLogin().equals(usuario)) {
			MessageDialog.openError(LoginDialog.getActiveShell(), 
					"Login", "Usuário informado não encontrado");
			return false;
		}
		
		if(!this.usuario.getSenha().equals(senha)) {
			MessageDialog.openError(LoginDialog.getActiveShell(), 
					"Login", "Senha informado não confere");
			return false;
		}
		
		MessageDialog.openInformation(LoginDialog.getActiveShell(), 
				"Login sistema", "Login efetuado com sucesso!");
		return true;
	}
	
}
