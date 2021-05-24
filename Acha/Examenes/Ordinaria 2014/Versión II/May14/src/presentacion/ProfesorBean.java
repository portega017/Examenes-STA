package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Alumno;
import datos.Asignatura;
import datos.Nota;
import negocio.Servicios;
@Named
@RequestScoped
public class ProfesorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Nota nota = new Nota();
	private String dni;
	private int idAsignatura;
	private String[] mmisMensajes= {"Introduzca los datos","Alumno Incorrecto","Asignatura Incorrecta","Alumno y asignatura incorrecta","Datos correctos"};
	int i = 0;
	public List<Nota> getListaNotas(){
		return miEJB.getListaNotas();
	}
	
	public void borrarNota(int id){
		miEJB.borrarNota(id);
	}
	
	public void addNota(){
		Alumno alumno = miEJB.getMiAlumno(dni);
		Asignatura asignatura = new Asignatura();
		asignatura=miEJB.getMiAsignatura(idAsignatura);
		if(alumno==null && asignatura==null){
			i=3;
		}else if(alumno==null){
			i=1;
		}else if(asignatura==null){
			 i=2;
		}else {
			i=4;
			nota.setAlumnoBean(alumno);
			nota.setAsignaturaBean(asignatura);
			miEJB.addNota(nota);
		}
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public String[] getMmisMensajes() {
		return mmisMensajes;
	}

	public void setMmisMensajes(String[] mmisMensajes) {
		this.mmisMensajes = mmisMensajes;
	}
	
	public String getMiMensajeFINAL(){
		return mmisMensajes[i];
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

}
