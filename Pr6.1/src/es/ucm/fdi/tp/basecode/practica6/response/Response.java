package es.ucm.fdi.tp.basecode.practica6.response;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;

/**
 * 
 * Interfaz de las notificaciones.
 *
 */
public interface Response extends java.io.Serializable{

	/**
	 * Ejecuta la notificacion.
	 * @param o observador.
	 */
	public void run (GameObserver o);
}
