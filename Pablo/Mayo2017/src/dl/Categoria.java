package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCategorias;
	private String nombreCategotria;

	public Categoria() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdCategorias() {
		return this.idCategorias;
	}

	public void setIdCategorias(int idCategorias) {
		this.idCategorias = idCategorias;
	}


	public String getNombreCategotria() {
		return this.nombreCategotria;
	}

	public void setNombreCategotria(String nombreCategotria) {
		this.nombreCategotria = nombreCategotria;
	}

}