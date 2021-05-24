package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Alumno;
import datos.Nota;
import negocio.Servicios;

@Named
@RequestScoped
public class AlumnoBean implements Serializable {
	
	/**
	 * 
	 */
	private String[] mensaje={"NO HAY NOTAS","DNI INCORRECTO","DATOS CORRECTOS"};
	private Alumno alumno=new Alumno();
	private boolean ok =false;
	private boolean vacia=true;
	private static final long serialVersionUID = 1L;
	private boolean notas=false;
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
	
	public void deleteAlumno(String dni){
		miEJB.borrarAlumno(dni);
	}
	
	public void addAlumno(){
		miEJB.addAlumno(alumno);
	}
	
	public List<Nota> getNotasAlumno(String dni){
		List<Nota> miLista= miEJB.getNOTASALUMNO(dni);
		if(miLista.isEmpty()==true){
			vacia=false;
		}
		return miLista;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String[] getMensaje() {
		return mensaje;
	}
	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}
	
	public String mostrarMensaje(){
		int i=2;
		boolean a = miEJB.dniCorrecto(alumno.getDni());
		getNotasAlumno(alumno.getDni());
		if(a==false){
			i=1;
		}else if(vacia==false){
			i=0;
		}else{
			notas=true;
		}
	return mensaje[i];	
	}
	
	
	public boolean isVacia() {
		return vacia;
	}
	
	public void setVacia(boolean vacia) {
		this.vacia = vacia;
	}
	public boolean isNotas() {
		return notas;
	}
	public void setNotas(boolean notas) {
		this.notas = notas;
	}
	
}
