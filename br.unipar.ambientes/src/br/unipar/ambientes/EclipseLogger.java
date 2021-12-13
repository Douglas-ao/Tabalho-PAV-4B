package br.unipar.ambientes;

import org.eclipse.core.runtime.Status;

import br.unipar.ambientes.aplicacao.interno.Activator;

public final class EclipseLogger {

	private Activator plugin;

    public EclipseLogger(Activator plugin) {
        super();
        this.plugin = plugin;
    }

    /**
     * Loga a mensagem de erro no log do pr�prio eclipse.
     * @param msg
     * @see #log(String, Exception)
     */
    public void logError(String msg, Exception e) {
        log(msg, Status.ERROR, e);
    }

    /**
     * Loga a mensagem de informa��o no log do pr�prio eclipse.
     * @param msg
     * @see #log(String, Exception)
     */
    public void logInfo(String msg) {
        log(msg, Status.INFO, null);
    }

    /**
     * Loga a mensagem e a exce��o no log do eclipse utilizando um objeto
     * {@link Status}.
     * @param msg
     *            - a mensagem
     * @param severity
     *            - a gravidade
     * @param e
     *            - a exce��o
     */
    public void log(String msg, int severity, Exception e) {
        plugin.getLog().log(new Status(severity, Activator.PLUGIN_ID, Status.OK, msg, e));
    }
	
}
