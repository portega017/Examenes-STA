package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ListaCategoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> misCategorias;
	
	@XmlElement(name="categoria")
	public List<Categoria> getMisCategorias() {
		if(misCategorias==null){
			misCategorias= new ArrayList<Categoria>();
		}
		return misCategorias;
	}
	public void setMisCategorias(List<Categoria> misCategorias) {
		this.misCategorias = misCategorias;
	}

}
