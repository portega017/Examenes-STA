package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Categoria database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c"),
	@NamedQuery(name="Categoria.findByName", query="SELECT c FROM Categoria c WHERE c.nombreCategotria = :nombre"),
	@NamedQuery(name="Categoria.findById", query="SELECT c FROM Categoria c WHERE c.idCategorias = :id"),
})
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