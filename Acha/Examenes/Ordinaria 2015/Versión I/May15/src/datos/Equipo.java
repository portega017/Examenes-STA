package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Equipo database table.
 * 
 */
@Entity
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int division;
	private String nombre;

	public Equipo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getDivision() {
		return this.division;
	}

	public void setDivision(int division) {
		this.division = division;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}