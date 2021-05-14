package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nota database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Nota.findALUMNO",query="SELECT n FROM Nota n WHERE n.alumno.dni=:dni"),
@NamedQuery(name="Nota.findAll", query="SELECT n FROM Nota n"),
@NamedQuery(name="Nota.addNota",query="SELECT n FROM Nota n WHERE n.alumno.dni=:dni AND n.asignatura.idAsignatura=:id")
})
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idNota;
	private String nota;
	private Alumno alumno;
	private Asignatura asignatura;

	public Nota() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdNota() {
		return this.idNota;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}


	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}


	//uni-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="Alumno_dni")
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	//uni-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name="Asignatura_idAsignatura")
	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

}