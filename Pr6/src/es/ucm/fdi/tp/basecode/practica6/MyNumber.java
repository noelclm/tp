package es.ucm.fdi.tp.basecode.practica6;

import java.io.Serializable;

public class MyNumber implements Serializable {
	private static final long serialVersionUID = 1L;
	private int n;
	
	public MyNumber (int n){
		this.n = n;
	}
	public int getValue(){
		return n;
	}

}
