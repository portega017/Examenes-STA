package pl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Tarea;

@Named
@SessionScoped
public class TareasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEjb;
	private Tarea tarea = new Tarea();
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	public void addTarea(String nombre) {
		miEjb.addTarea(nombre);
	
	}
	

}
