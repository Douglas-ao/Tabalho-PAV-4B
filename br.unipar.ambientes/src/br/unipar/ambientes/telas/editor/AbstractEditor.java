package br.unipar.ambientes.telas.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.ResourceManager;

import br.unipar.ambientes.aplicacao.exception.ValidationException;
import br.unipar.ambientes.aplicacao.helper.MessageHelper;
import br.unipar.ambientes.aplicacao.helper.ValidatorHelper;
import br.unipar.ambientes.aplicacao.interno.Activator;
import br.unipar.ambientes.telas.dialog.ErroDialog;

public abstract class AbstractEditor extends EditorPart implements ISaveablePart2{
	//Cada Editor que implentar deverá criar o seu FormToolkit, por causa do WindowBuilder
	private FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	/** O {@code form} que será base para criação dos campos. */
	protected ScrolledForm form;
	protected Button btnSalvar;
	private Button btnExcluir;
	private Composite compositeBottom;
	
	private boolean bottomVisible = true;
	private boolean showExcluir = true;
	private boolean showSalvar = true;
	private String textoExcluir;
	private String textoSalvar;
	private String mensagemExcluir = "Tem certeza que deseja excluir o registro?";

	public AbstractEditor() {}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(parent, SWT.NONE);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(new GridLayout(1, false));
		
		form = formToolkit.createScrolledForm(composite);
		form.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.paintBordersFor(form);
		form.getBody().setLayout(new GridLayout(1, false));
		form.setExpandHorizontal(true);
		form.setExpandVertical(true);

		Composite compositeTop = formToolkit.createComposite(form.getBody(), SWT.NONE);
		compositeTop.setLayout(new GridLayout(6, false));
		compositeTop.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.paintBordersFor(compositeTop);
		
		compositeBottom = new Composite(composite, SWT.BORDER);
		compositeBottom.setVisible(bottomVisible);
		compositeBottom.setLayout(new GridLayout(10, false));
		compositeBottom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		formToolkit.adapt(compositeBottom);
		formToolkit.paintBordersFor(compositeBottom);
		
		//CRIA O BOTÃO DE SALVAR
		this.btnSalvar = new Button(compositeBottom, SWT.NONE);
		this.btnSalvar.setVisible(showSalvar);
		this.btnSalvar.setImage(ResourceManager.getPluginImage("unipar.br.pav.turma", "assets/funcoes/save-gnome32.png"));
		this.btnSalvar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				salvarRegistro();
			}
		});
		this.btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(this.btnSalvar, true, true);
		this.btnSalvar.setText("Salvar");
		//MODIFICAR TEXTO DO BOTÃO DE SALVAR
		if(textoSalvar != null && !textoSalvar.trim().equals(""))
			this.btnSalvar.setText(textoSalvar);
		
		//CRIA O BOTÃO EXCLUIR
		if(showExcluir){
			this.btnExcluir = new Button(compositeBottom, SWT.NONE);
			this.btnExcluir.setImage(ResourceManager.getPluginImage("unipar.br.pav.turma", "assets/funcoes/delete-gnome32.png"));
			this.btnExcluir.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (MessageHelper.openConfirm(mensagemExcluir))
						excluirRegistro();
				}
			});
			this.btnExcluir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
			formToolkit.adapt(this.btnExcluir, true, true);
			this.btnExcluir.setText("Excluir");
			//MODIFICAR TEXTO DO BOTÃO DE EXCLUIR
			if(textoExcluir != null && textoExcluir.trim().equals(""))
				this.btnExcluir.setText(textoExcluir);
			//ADEQUAR MENSAGEM DE EXCLUIR
			if(mensagemExcluir != null && mensagemExcluir.trim().equals(mensagemExcluir))
				mensagemExcluir = "Tem certeza que deseja excluir o registro?";
		}
		
		addComponents(compositeTop);
		
		form.setContent(compositeTop);
		form.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		form.getVerticalBar().setPageIncrement(15);
		form.getVerticalBar().setIncrement(15);
	}
	
	/**
	 * Método para implementar ação para o botão 'Salvar'
	 */
	protected abstract void salvarRegistro();

	/**
	 * Método para implementar ação para o botão 'Excluir'
	 */
	protected abstract void excluirRegistro();

	protected abstract void addComponents(Composite compositeTop);
	
	protected Button addBotao(String texto, final Action acao, String path, boolean visible){
		if(!visible)
			return null;
		
		Button btnNewButton = new Button(this.compositeBottom, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				acao.run();
			}
		});
		btnNewButton.setImage(ResourceManager.getPluginImage(Activator.PLUGIN_ID, path));
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText(texto);
		
		return btnNewButton;
	}
	
	/**
	 * Adiciona o botão para reativar o registro.
	 *
	 * @param acao a ação do botão, quando pressionado
	 * @param visible indicativo ou codição para visualização do botão
	 * @return o botão de desativar
	 * @since 4.25.0
	 */
	protected Button addBotaoReativar(final Action acao, boolean visible){
		return addBotao("Reativar", acao, "assets/redo/redo32.png", visible);
	}

	/**
	 * Fecha o {@code Editor} que está em ativo.
	 */
	protected void closeThisEditor() {
		getEditorSite().getPart().getSite().getWorkbenchWindow().getActivePage().closeEditor(this, false);
	}
	
	@Override
	public void setFocus() {}
	
	@Override
	public void doSaveAs() {}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		monitor.beginTask("Salvando registro...", IProgressMonitor.UNKNOWN);
		salvarRegistro();
		monitor.done();
	}

	public void doDeleteDisable(IProgressMonitor monitor) {
		monitor.beginTask((showExcluir ? "Excluindo" : "Desativando")+" registro...", IProgressMonitor.UNKNOWN);
		excluirRegistro();
		monitor.done();
	}
	
	protected void addEnterNextListener(final Control controle){
		controle.addTraverseListener(new TraverseListener() {
			
			@Override
			public void keyTraversed(TraverseEvent e) {
				if(e.character == SWT.CR)
					controle.traverse(SWT.TRAVERSE_TAB_NEXT);
			}
		});
	}
	
	public void setShowExcluir(boolean showExcluir) {
		this.showExcluir = showExcluir;
	}
	
	public void setShowSalvar(boolean showSalvar) {
		this.showSalvar = showSalvar;
	}
	
	public void setTextoSalvar(String texto){
		this.textoSalvar = texto;
	}
	
	public void setTextoExcluir(String texto){
		this.textoExcluir = texto;
	}
	
	/**
	 * Insere a mensagem de confirmação para excluir registro.
	 *
	 * @param mensagemExcluir a mensagem de excluir
	 */
	public void setMensagemExcluir(String mensagemExcluir) {
		this.mensagemExcluir = mensagemExcluir;
	}
	
	/**
	 * Definie se será criado os botões na tela.
	 *
	 * @param show informar 'true' para criar os botões
	 */
	public void showBottom(boolean show){
		this.bottomVisible = show;
	}
	
	/**
	 * Modifica a cor de fundo do editor
	 * @param cor a cor escolhida
	 */
	public void setBackgroudColor(Color cor){
		this.formToolkit.setBackground(cor);
	}
	
	/**
	 * Insere o título do {@code Form} do {@code Editor}
	 * @param titulo o título para o form
	 */
	public void setTitulo(String titulo){
		this.form.setText(titulo);
	}
	
	/**
	 * Insere a imagem do {@link #form} do {@code Editor}. Deverá ser informada uma imagem com tamanho 32x32
	 * @param image a imagem do form
	 */
	public void setImagem(Image image){
		form.setImage(image);
	}

	@Override
	public int promptToSaveOnClose() {
		boolean ret = MessageHelper.openConfirm("O registro foi alterado. Deseja salvar as alterações?");
		
		if(ret){
			salvarRegistro();
		}
		
		return CANCEL;
	}
	
	public Button getBotaoExcluir() {
		return btnExcluir;
	}
	
	/**
	 * Valida o objeto informado usando o {@link ValidatorHelper#validar(Object)} e 
	 * verifica também se o sistema está bloqueado através do {@link BloqueioSistemaHelper#validarSistemaBloqueado()}
	 *
	 * @param obj o objeto a ser validado
	 * @throws ValidationException a exceção da validação do objeto
	 */
	protected void validar(Object obj) throws ValidationException {
		try {
			ValidatorHelper.validar(obj);
		} catch (ValidationException e) {
			throw e;
		}
	}
	
	/**
	 * Abre o {@code ErroDialog} com a mensagem de erro informada
	 *
	 * @param mensagem a mensagem do erro/exceção
	 */
	protected void showValidationDialog(String mensagem) {
		new ErroDialog(mensagem).open();
	}
	
	public void redrawBotoes(){
		compositeBottom.redraw();
	}
}