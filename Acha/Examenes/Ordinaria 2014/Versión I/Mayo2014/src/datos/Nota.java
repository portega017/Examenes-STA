package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Nota database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Nota.findAll", query="SELECT n FROM Nota n"),
	@NamedQuery(name="Nota.findAlumno", query="SELECT n FROM Nota n WHERE n.alumnoBean.dni =:dni")
	

})

public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idNota;
	private double nota;
	private Alumno alumnoBean;
	private Asignatura asignaturaBean;

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


	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}


	//uni-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="alumno")
	public Alumno getAlumnoBean() {
		return this.alumnoBean;
	}

	public void setAlumnoBean(Alumno alumnoBean) {
		this.alumnoBean = alumnoBean;
	}


	//uni-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name="asignatura")
	public Asignatura getAsignaturaBean() {
		return this.asignaturaBean;
	}

	public void setAsignaturaBean(Asignatura asignaturaBean) {
		this.asignaturaBean = asignaturaBean;
	}

}