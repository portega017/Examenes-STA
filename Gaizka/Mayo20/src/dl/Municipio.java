package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Municipio database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m"),
	@NamedQuery(name="Municipio.findById", query="SELECT m FROM Municipio m WHERE m.id = :id")
})
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int habitantes;
	private String nombre;

	public Municipio() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getHabitantes() {
		return this.habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}