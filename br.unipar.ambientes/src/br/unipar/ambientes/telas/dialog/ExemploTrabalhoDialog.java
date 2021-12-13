package br.unipar.ambientes.telas.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;

import br.unipar.ambientes.aplicacao.enums.SexoEnum;
import br.unipar.ambientes.aplicacao.helper.MessageHelper;

public class ExemploTrabalhoDialog extends TitleAreaDialog {
	
	private Text txtNome;
	private Text txtTag;
	private Table tableTags;
	
	private List<String> tags = new ArrayList<String>();
	private TableViewer tvTags;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ExemploTrabalhoDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Exemplo para o trabalho");
		setTitle("Exemplo");
		
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//Cria uma etiqueta chamada Nome
		Label lblNome = new Label(container, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome");
		
		//Cria uma campo de entrada de texto
		txtNome = new Text(container, SWT.BORDER);
		txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblSexo = new Label(container, SWT.NONE);
		lblSexo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSexo.setText("Sexo");
		
		//Cria a caixa de combinações para escolher o Sexo
		ComboViewer cvSexo = new ComboViewer(container, SWT.READ_ONLY);
		Combo comboSexo = cvSexo.getCombo();
		comboSexo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		comboSexo.select(0);
		//Define o tipo de conteúdo que será utilizado (padrão é usar Array)
		cvSexo.setContentProvider(ArrayContentProvider.getInstance());
		//Insere nas combinações a lista que deverá ser exibida
		cvSexo.setInput(SexoEnum.values());
		//Definimos a forma que será exibido esses dados
		cvSexo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				SexoEnum sexoEnum = (SexoEnum) element;
				return sexoEnum.getDescricao();
			}
		});
		new Label(container, SWT.NONE);
		
		Label lblTag = new Label(container, SWT.NONE);
		lblTag.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTag.setText("Tag");
		
		txtTag = new Text(container, SWT.BORDER);
		txtTag.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAdd = new Button(container, SWT.NONE);
		btnAdd.setText("Adicionar");
		btnAdd.setImage(ResourceManager.getPluginImage("br.unipar.ambientes", "assets/funcoes/plus16.png"));
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Obtemos o valor digitado pelo usuário
				String tag = txtTag.getText();
				//Validamos se foi digitado alguma coisa
				if(tag.equals("")) {
					MessageHelper.openError("Não foi informada a TAG");
					return;
				}
				
				//Caso a TAG já foi adicionada, não iremos inserir novamente
				if(tags.contains(tag)) {
					txtTag.setText("");
					return;
				}
				
				//Insere o texto obtido na lista de tags
				tags.add(tag);
				//Atualiza a tabela, para a informação nova apareça
				tvTags.refresh();
				//Limpa o campo de texto para inserir uma nova tag
				txtTag.setText("");
			}
		});
		
		//Cria uma nova TableViewer
		tvTags = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		//Cria a table e vincula ela a tableViewer
		tableTags = tvTags.getTable();
		tableTags.setHeaderVisible(true);
		tableTags.setLinesVisible(true);
		tableTags.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		//Define como será o conteúdo da tabela (padrão utilizamos array)
		tvTags.setContentProvider(ArrayContentProvider.getInstance());
		//Insere na tabela a lista que deverá ser exibida 
		tvTags.setInput(tags);
		
		//Cria uma nova coluna para a tabela
		TableViewerColumn tvcTag = new TableViewerColumn(tvTags, SWT.NONE);
		//Definimos a forma que será exibido esses dados
		tvcTag.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return element == null ? "" : element.toString();
			}
		});
		TableColumn tblclmnTag = tvcTag.getColumn();
		tblclmnTag.setWidth(100);
		tblclmnTag.setText("TAG");
		
		//Cria um menu de contexto para a tabela
		Menu menu = new Menu(tableTags);
		tableTags.setMenu(menu);
		
		//Cria um item dentro desse menu de contexto para remover o item
		MenuItem mntmRemover = new MenuItem(menu, SWT.NONE);
		mntmRemover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Comando padrão para obter o item selecionado na lista
				IStructuredSelection selecao = (IStructuredSelection) tvTags.getSelection();
				//Pega o primeiro item selecionado
				String tag = (String) selecao.getFirstElement();
				if(tag == null)
					return;
				
				//Remove o item selecionado da lista
				tags.remove(tag);
				//Atualiza a tabela para visualizar as modificações
				tvTags.refresh();
			}
		});
		mntmRemover.setText("Remover");
		tvTags.refresh();
		
		return area;
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
		return new Point(450, 360);
	}

}
