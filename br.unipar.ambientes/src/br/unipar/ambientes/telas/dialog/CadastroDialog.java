package br.unipar.ambientes.telas.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.unipar.ambientes.aplicacao.enums.EstadoCivilEnum;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;

public class CadastroDialog extends TitleAreaDialog {
	
	private Text txtNomeCompleto;
	private Button btnMasculino;
	private Button btnFeminino;
	private Button btnOutro;
	private ComboViewer cvEstadoCivil;
	private Text txtObservacoes;
	private Text txtNome;
	private Table tableNomes;
	
	private List<String> listaNomes = new ArrayList<>();
	private TableViewer tvNomes;
	private Button btnAdicionar;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CadastroDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Cadastro do aluno");
		setMessage("Informe os dados para o cadastro");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblNomeCompleto = new Label(container, SWT.NONE);
		lblNomeCompleto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNomeCompleto.setText("Nome completo");
		
		txtNomeCompleto = new Text(container, SWT.BORDER);
		txtNomeCompleto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Group grpOutrasInformacoes = new Group(container, SWT.NONE);
		grpOutrasInformacoes.setLayout(new GridLayout(2, false));
		grpOutrasInformacoes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));
		grpOutrasInformacoes.setText("Outras informações");
		
		Label lblObservacoes = new Label(grpOutrasInformacoes, SWT.NONE);
		lblObservacoes.setText("Observações");
		
		txtObservacoes = new Text(grpOutrasInformacoes, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		GridData gd_txtObservacoes = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtObservacoes.heightHint = 52;
		txtObservacoes.setLayoutData(gd_txtObservacoes);
		
		Button btnAceitoOsTermos = new Button(grpOutrasInformacoes, SWT.CHECK);
		btnAceitoOsTermos.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnAceitoOsTermos.setText("Aceito os termos");
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.FILL, false, true, 4, 1);
		gd_tabFolder.heightHint = 81;
		tabFolder.setLayoutData(gd_tabFolder);
		
		TabItem tbtmDadosPessoais = new TabItem(tabFolder, SWT.NONE);
		tbtmDadosPessoais.setText("Dados pessoais");
		
		Composite compositeDadosPessoais = new Composite(tabFolder, SWT.NONE);
		tbtmDadosPessoais.setControl(compositeDadosPessoais);
		compositeDadosPessoais.setLayout(new GridLayout(3, false));
		
		Label lblEstadoCivil = new Label(compositeDadosPessoais, SWT.NONE);
		lblEstadoCivil.setText("Estado Civil");
		
		//Cria a caixa de combinações para escolher
		cvEstadoCivil = new ComboViewer(compositeDadosPessoais, SWT.READ_ONLY);
		Combo comboEstadoCivil = cvEstadoCivil.getCombo();
		comboEstadoCivil.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		comboEstadoCivil.select(0);
		
		//Define o tipo de conteúdo que será utilizado (padrão é usar Array)
		cvEstadoCivil.setContentProvider(ArrayContentProvider.getInstance());
		//Insere nas combinações a lista que deverá ser exibida
		cvEstadoCivil.setInput(EstadoCivilEnum.values());
		//Definimos a forma que será exibido esses dados
		cvEstadoCivil.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				EstadoCivilEnum estadoCivilEnum = (EstadoCivilEnum) element;
				return estadoCivilEnum.getDescricao();
			}
		});
		
		Label lblDataNasc = new Label(compositeDadosPessoais, SWT.NONE);
		lblDataNasc.setText("Data Nasc");
		
		DateTime dtNascimento = new DateTime(compositeDadosPessoais, SWT.BORDER);
		DateTime hrNascimento = new DateTime(compositeDadosPessoais, SWT.BORDER | SWT.TIME);
		
		Label lblIdade = new Label(compositeDadosPessoais, SWT.NONE);
		lblIdade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIdade.setText("Idade");
		
		Spinner spinnerIdade = new Spinner(compositeDadosPessoais, SWT.BORDER);
		spinnerIdade.setFont(SWTResourceManager.getFont("Segoe UI Black", 9, SWT.NORMAL));
		spinnerIdade.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		spinnerIdade.setMaximum(110);
		spinnerIdade.setMinimum(2);
		new Label(compositeDadosPessoais, SWT.NONE);
		
		TabItem tbtmSexo = new TabItem(tabFolder, SWT.NONE);
		tbtmSexo.setText("Sexo");
		
		Composite compositeSexo = new Composite(tabFolder, SWT.NONE);
		tbtmSexo.setControl(compositeSexo);
		compositeSexo.setLayout(new GridLayout(4, false));
		
		Label lblSexo = new Label(compositeSexo, SWT.NONE);
		lblSexo.setText("Sexo");
		
		btnMasculino = new Button(compositeSexo, SWT.RADIO);
		btnMasculino.setText("Masculino");
		
		btnFeminino = new Button(compositeSexo, SWT.RADIO);
		btnFeminino.setText("Feminino");
		
		btnOutro = new Button(compositeSexo, SWT.RADIO);
		btnOutro.setSelection(true);
		btnOutro.setText("Outro");
		
		TabItem tabItemTabela = new TabItem(tabFolder, SWT.NONE);
		tabItemTabela.setText("Tabela");
		
		Composite compositeTabela = new Composite(tabFolder, SWT.NONE);
		tabItemTabela.setControl(compositeTabela);
		compositeTabela.setLayout(new GridLayout(3, false));
		
		Label lblNome = new Label(compositeTabela, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		txtNome = new Text(compositeTabela, SWT.BORDER);
		txtNome.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				String nome = txtNome.getText();
				if(listaNomes.contains(nome)) {
					txtNome.setText("");
					return;
				}
				
				listaNomes.add(nome);
				tvNomes.refresh();
				
				txtNome.setText("");
			}
		});
		GridData gd_txtNome = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtNome.widthHint = 145;
		txtNome.setLayoutData(gd_txtNome);
		
		btnAdicionar = new Button(compositeTabela, SWT.NONE);
		btnAdicionar.setImage(ResourceManager.getPluginImage("br.unipar.ambientes", "assets/funcoes/plus16.png"));
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nome = txtNome.getText();
				if(listaNomes.contains(nome)) {
					txtNome.setText("");
					return;
				}
				
				listaNomes.add(nome);
				tvNomes.refresh();
				
				txtNome.setText("");
			}
		});
		btnAdicionar.setText("Adicionar");
		
		tvNomes = new TableViewer(compositeTabela, SWT.BORDER | SWT.FULL_SELECTION);
		tvNomes.setContentProvider(ArrayContentProvider.getInstance());
		tvNomes.setInput(listaNomes);
		tableNomes = tvNomes.getTable();
		tableNomes.setLinesVisible(true);
		tableNomes.setHeaderVisible(true);
		tableNomes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		
		TableViewerColumn tvcNome = new TableViewerColumn(tvNomes, SWT.NONE);
		tvcNome.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return element == null ? "" : element.toString();
			}
		});
		TableColumn tblclmnNome = tvcNome.getColumn();
		tblclmnNome.setWidth(160);
		tblclmnNome.setText("Nome");
		
		Menu menu = new Menu(tableNomes);
		tableNomes.setMenu(menu);
		
		MenuItem mntmRemover = new MenuItem(menu, SWT.NONE);
		mntmRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Comando padrão para obter o item selecionado na lista
				IStructuredSelection selecao = (IStructuredSelection) tvNomes.getSelection();
				//Pega o primeiro item selecionado
				String nome = (String) selecao.getFirstElement();
				if(nome == null)
					return;
				
				//Remove o item selecionado da lista
				listaNomes.remove(nome);
				//Atualiza a tabela para visualizar as modificações
				tvNomes.refresh();
			}
		});
		mntmRemover.setImage(ResourceManager.getPluginImage("br.unipar.ambientes", "assets/funcoes/deletarCancelar16.png"));
		mntmRemover.setText("Remover");

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Valeu campeão", false);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(510, 488);
	}
	
	@Override
	protected void okPressed() {
		String nomeCompleto = txtNomeCompleto.getText();
		boolean masculino = btnMasculino.getSelection();
		
		super.okPressed();
	}
}
