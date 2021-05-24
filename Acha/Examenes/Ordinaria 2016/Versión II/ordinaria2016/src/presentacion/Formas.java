package presentacion;

import java.io.Serializable;
public class Formas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	private String propiedades;
	public String getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(String propiedades) {
		this.propiedades = propiedades;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
