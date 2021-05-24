package datos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Inventario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Producto> inventario ;
	
	
	
	@XmlElement(name="producto")
	public List<Producto> getInventario() {
		if(inventario==null){
			inventario=new ArrayList<>();
		}
		return inventario;
	}
	public void setInventario(List<Producto> inventario) {
		this.inventario = inventario;
	}
}
