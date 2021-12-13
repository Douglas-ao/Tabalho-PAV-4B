package br.unipar.ambientes.telas.dialog;

import java.math.BigDecimal;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.unipar.ambientes.aplicacao.enums.ClassificacaoEnum;
import br.unipar.ambientes.controlador.IndiceMassaCorporalController;

public class CalculadoraIMCDialog extends TitleAreaDialog {
	
	private IndiceMassaCorporalController contoller;
	private ClassificacaoEnum classificacaoEnum;
	
	private Text txtPeso;
	private Text txtAltura;
	private Text txtResultado;
	private Label lblResultadoImc;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public CalculadoraIMCDialog(Shell parentShell) {
		super(parentShell);
		this.contoller = new IndiceMassaCorporalController();
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Informe o peso e altura para calcular o IMC");
		setTitle("Calculadora IMC");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblPeso = new Label(container, SWT.NONE);
		lblPeso.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblPeso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPeso.setText("Peso");
		
		txtPeso = new Text(container, SWT.BORDER);
		txtPeso.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtPeso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		txtPeso.setMessage("Informe o peso em Kg");
		txtPeso.setTextLimit(5);
		
		Label lblAltura = new Label(container, SWT.NONE);
		lblAltura.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblAltura.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAltura.setText("Altura");
		
		txtAltura = new Text(container, SWT.BORDER);
		txtAltura.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		txtAltura.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		txtAltura.setMessage("Informe a altura em metros");
		txtAltura.setTextLimit(4);
		new Label(container, SWT.NONE);
		
		Button btnCalcular = new Button(container, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				classificacaoEnum = contoller.obterClassificacaoImc(txtPeso.getText(), txtAltura.getText());
				txtResultado.setText(classificacaoEnum.getDescricao());
				mostrarResultadoLabel(classificacaoEnum);
			}
		});
		btnCalcular.setText("CALCULAR");
		
		Button btnLimpar = new Button(container, SWT.NONE);
		btnLimpar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO LIMPAR OS CAMPOS
			}
		});
		btnLimpar.setText("LIMPAR");
		
		Label lblResultado = new Label(container, SWT.NONE);
		lblResultado.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResultado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblResultado.setText("Resultado");
		
		txtResultado = new Text(container, SWT.BORDER);
		txtResultado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
//		txtResultado.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		txtResultado.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(container, SWT.NONE);
		
		lblResultadoImc = new Label(container, SWT.NONE);
		lblResultadoImc.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		GridData gd_lblResultadoImc = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_lblResultadoImc.heightHint = 40;
		lblResultadoImc.setLayoutData(gd_lblResultadoImc);
		lblResultadoImc.setText("");

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
		return new Point(310, 321);
	}
	
	private void mostrarResultadoLabel(ClassificacaoEnum classificacaoEnum) {
		lblResultadoImc.setForeground(classificacaoEnum.getColor());
		lblResultadoImc.setText(classificacaoEnum.getDescricao());
	}

}
