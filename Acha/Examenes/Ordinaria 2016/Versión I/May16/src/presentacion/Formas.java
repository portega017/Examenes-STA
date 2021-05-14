package presentacion;

import java.io.Serializable;



public class Formas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	private String misMedias;
	
	
	public String getMisMedias() {
		return misMedias;
	}
	public void setMisMedias(String misMedias) {
		this.misMedias = misMedias;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
