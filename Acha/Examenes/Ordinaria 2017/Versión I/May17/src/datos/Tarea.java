package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tarea database table.
 * 
 */
@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTarea;
	private String nombreTarea;

	public Tarea() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}


	public String getNombreTarea() {
		return this.nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

}