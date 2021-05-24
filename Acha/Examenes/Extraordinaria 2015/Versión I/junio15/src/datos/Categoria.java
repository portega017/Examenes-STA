package datos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	
	
	@XmlElement
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
