package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Asignatura database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Asignatura.findAll", query="SELECT a FROM Asignatura a"),
	@NamedQuery(name="Asignatura.findNombre", query="SELECT a FROM Asignatura a WHERE a.nombre =:nombre")
})
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAsignatura;
	private String nombre;

	public Asignatura() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdAsignatura() {
		return this.idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}