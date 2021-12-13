package br.unipar.ambientes.telas.editor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import br.unipar.ambientes.aplicacao.enums.SexoEnum;
import br.unipar.ambientes.aplicacao.helper.MessageHelper;
import br.unipar.ambientes.telas.editor.editorInput.CadastroEditorInput;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;

public class CadastroEditor extends AbstractEditor {

	public static final String ID = "br.unipar.ambientes.telas.editor.CadastroEditor"; //$NON-NLS-1$
	private Text txtNome;
	private Text txtCpf;
	private Text txtRa;
	private Label lblNewLabel;
	private Text text;
	private Label lblSexo;
	private Label lblEndereo;
	private Text textEndereco;
	private Label lblNumero;
	private Label lblBairro;
	private Label lblCidade;
	private Label lblUf;
	private Text textNumero;
	private Text textBairro;
	private Text textCidade;
	private Text textUF;
	private Combo combo;
	private ComboViewer cvSexo;

	@Override
	protected void salvarRegistro() {
		if(txtRa.getText().isEmpty()) {
			MessageHelper.openError("Não foi informado o R.A");
			return;
		}
		
		if(txtRa.getText().length() < 6 || txtRa.getText().length() > 8) {
			MessageHelper.openError("O R.A deve conter entre 6 e 8 caracteres");
			return;
		}
		
		if(!Pattern.matches( "\\d+", txtRa.getText())) {
			MessageHelper.openError("O R.A deve conter só numeros");
			return;
			
		}
		
		if(txtNome.getText().isEmpty()) {
			MessageHelper.openError("Não foi informado o nome");
			return;
		}
		
		if(txtNome.getText().length() < 10 || txtNome.getText().length() > 120) {
			MessageHelper.openError("O nome deve conter entre 10 e 120 caracteres");
			return;
		}
		
		if(txtCpf.getText().isEmpty()) {
			MessageHelper.openError("Não foi informado o CPF");
			return;
		}
		
		if(!Pattern.matches( "\\d+", txtCpf.getText())) {
			MessageHelper.openError("O CPF deve conter só numeros");
			return;
		}
		
		if(txtCpf.getText().length() != 11) {
			MessageHelper.openError("O CPF deve conter 11 numeros");
			return;
		}
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(text.getText());
			if(date.after(new Date())){
				MessageHelper.openError("Data de nascimento invalido");
				return;
			}
		} catch (ParseException e) {
			MessageHelper.openError("Data de nascimento invalido");
			return;
		}
		
		if(textEndereco.getText().isEmpty() || textEndereco.getText().length() > 60) {
			MessageHelper.openError("O Endereço informado não é valido");
			return;
		}
		
		if(!Pattern.matches( "\\d+", textNumero.getText())) {
			MessageHelper.openError("Numero de endereco invalido");
			return;
		}

		MessageHelper.openConfirm("Cadastro realizado com sucesso");
		

	}

	@Override
	protected void addComponents(Composite compositeTop) {
		compositeTop.setLayout(new GridLayout(2, false));
		
		Label lblRa = new Label(compositeTop, SWT.NONE);
		lblRa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRa.setText("R.A");
		
		txtRa = new Text(compositeTop, SWT.BORDER);
		txtRa.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblNome = new Label(compositeTop, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		txtNome = new Text(compositeTop, SWT.BORDER);
		txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCpf = new Label(compositeTop, SWT.NONE);
		lblCpf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCpf.setText("CPF");
		
		txtCpf = new Text(compositeTop, SWT.BORDER);
		GridData gdTxtCpf = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gdTxtCpf.widthHint = 147;
		txtCpf.setLayoutData(gdTxtCpf);
		
		lblNewLabel = new Label(compositeTop, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel.setText("Data de Nascimento");
		
		text = new Text(compositeTop, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 162;
		text.setLayoutData(gd_text);
		
		lblSexo = new Label(compositeTop, SWT.NONE);
		lblSexo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSexo.setText("Sexo");
		
		cvSexo = new ComboViewer(compositeTop, SWT.READ_ONLY);
		cvSexo.setContentProvider(ArrayContentProvider.getInstance());
		cvSexo.setInput(SexoEnum.values());
		cvSexo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				SexoEnum estadoCivilEnum = (SexoEnum) element;
				return estadoCivilEnum.getDescricao();
			}
		});
		combo = cvSexo.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblEndereo = new Label(compositeTop, SWT.NONE);
		lblEndereo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEndereo.setText("Endere\u00E7o");
		
		textEndereco = new Text(compositeTop, SWT.BORDER);
		textEndereco.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNumero = new Label(compositeTop, SWT.NONE);
		lblNumero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNumero.setText("Numero");
		
		textNumero = new Text(compositeTop, SWT.BORDER);
		textNumero.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblBairro = new Label(compositeTop, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBairro.setText("Bairro");
		
		textBairro = new Text(compositeTop, SWT.BORDER);
		textBairro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCidade = new Label(compositeTop, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCidade.setText("Cidade");
		
		textCidade = new Text(compositeTop, SWT.BORDER);
		textCidade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblUf = new Label(compositeTop, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUf.setText("UF");
		
		textUF = new Text(compositeTop, SWT.BORDER);
		textUF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compositeTop, SWT.NONE);
		new Label(compositeTop, SWT.NONE);
	}
	
	public CadastroEditor() {
	}
	
	@Override
	protected void excluirRegistro() {
		// NÃ£o faremos nada
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		CadastroEditorInput editorInput = new CadastroEditorInput();
		
		setInput(editorInput);
		setSite(site);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

}
