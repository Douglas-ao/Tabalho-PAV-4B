package br.unipar.ambientes.aplicacao.helper;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * A classe de auxilio para os Editors.
 * Fornece alguns métodos úteis ao manipular editores, tais como abrir, reabrir e fechar editor.
 * 
 * @author Filipe Wutzke
 */
public class EditorHelper {
	
	private EditorHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Abre o editor do ID informado.
	 * Deverá ser informado o editorInput e o ID do editor que deseja abrir.
	 *
	 * @param editorInput o {@link IEditorInput} do editor que deseja abrir
	 * @param id a {@code String} com a ID do editor a ser aberto
	 * @author Filipe Wutzke
	 */
	public static void abrirEditor(IEditorInput editorInput, String id){
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(editorInput, id);
		} catch (PartInitException e) {
			e.printStackTrace();
			MessageHelper.openErrorStatus("Ocorreu um erro ao abrir a tela de edição", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			MessageHelper.openErrorStatus("Ocorreu um erro ao abrir a tela de edição", e.getMessage());
		}
	}
	
	/**
	 * Fecha o editor do EditorInput informado 
	 * 
	 * @param editorInput o {@link IEditorInput} do editor que deseja fechar
	 * @author Filipe Wutzke
	 */
	public static void fecharEditor(IEditorInput editorInput) {
		fecharEditor(editorInput, false);
	}
	
	/**
	 * Fecha o editor do EditorInput informado 
	 *
	 * @param editorInput o {@link IEditorInput} do editor que deseja fechar
	 * @param salvar se deve salvar antes de fechar
	 * @author Filipe Wutzke
	 */
	public static void fecharEditor(IEditorInput editorInput, boolean salvar) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart iEditorPart = page.findEditor(editorInput);
		page.closeEditor(iEditorPart, salvar);
	}
}
