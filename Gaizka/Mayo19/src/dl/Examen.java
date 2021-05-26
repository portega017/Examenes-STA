package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Examenes database table.
 * 
 */
@Entity
@Table(name="Examenes")
@NamedQueries({
	@NamedQuery(name="Examen.findAll", query="SELECT e FROM Examen e"),
	@NamedQuery(name="Examen.findById", query="SELECT e FROM Examen e WHERE e.idExamen = :id"),
	@NamedQuery(name="Examen.findByAsignatura", query="SELECT e FROM Examen e WHERE e.asignatura = :asignatura")
})
public class Examen implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idExamen;
	private int alumnos;
	private String asignatura;

	public Examen() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdExamen() {
		return this.idExamen;
	}

	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}


	@Column(name="Alumnos")
	public int getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(int alumnos) {
		this.alumnos = alumnos;
	}


	@Column(name="Asignatura")
	public String getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

}