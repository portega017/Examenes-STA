package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Alumno;
import negocio.Servicios;

@Named
@RequestScoped
public class AlumnoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Alumno alumno=new Alumno();
	@EJB
	private Servicios miEJB;

	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	public List<Alumno> getListaAlumnos(){
		return miEJB.getListaAlumno();
	}
	
	public void borrarAlumno(String dni){
		miEJB.borrarAlumno(dni);
	}
	
	public void addAllumno(){
		miEJB.addAlumno(alumno);
	}
	
	
	
}
