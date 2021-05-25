package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tarea database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t"),
	@NamedQuery(name="Tarea.findByName", query="SELECT t FROM Tarea t WHERE t.nombreTareas = :nombre"),
	@NamedQuery(name="Tarea.findById", query="SELECT t FROM Tarea t WHERE t.idTareas = :id"),
})
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTareas;
	private String nombreTareas;

	public Tarea() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdTareas() {
		return this.idTareas;
	}

	public void setIdTareas(int idTareas) {
		this.idTareas = idTareas;
	}


	public String getNombreTareas() {
		return this.nombreTareas;
	}

	public void setNombreTareas(String nombreTareas) {
		this.nombreTareas = nombreTareas;
	}

}