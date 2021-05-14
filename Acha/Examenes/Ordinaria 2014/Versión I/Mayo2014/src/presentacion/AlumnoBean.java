package presentacion;

import java.io.Serializable;
import java.util.List;


import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;

import javax.inject.Named;

import datos.Alumno;
import negocio.Servicios;

@RequestScoped
@Named
public class AlumnoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Alumno alumno = new Alumno();
	private String sms="¡Rellena el campo vacio!";
	@EJB
	private Servicios miEJB;
	
	public Alumno getAlumno() {
		return alumno;
	}

	public List<Alumno> getAlumnos(){
		return miEJB.getAlumnos();
	}
	
	
	public void deleteAlumno(String dni){
		miEJB.deleteAlumno(dni);
	}
	
	
	public void addAlumno(){
		miEJB.addAlumno(alumno);
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}
}
