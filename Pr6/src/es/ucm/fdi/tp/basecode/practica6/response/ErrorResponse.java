package es.ucm.fdi.tp.basecode.practica6.response;

import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;

/**
 * 
 * Respuesta de error.
 *
 */
public class ErrorResponse implements Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	/**
	 * Constructor parametrizado.
	 * @param msg mensaje.
	 */
	public ErrorResponse(String msg){
		this.msg=msg;
	}

	@Override
	public void run(GameObserver o) {
		
		o.onError(msg); 

	}

}
