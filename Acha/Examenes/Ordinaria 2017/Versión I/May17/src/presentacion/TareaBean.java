package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import datos.Tarea;
import negocio.Servicios;

@Named
@RequestScoped
public class TareaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Tarea tarea=new Tarea();
	

	public void addTarea(String nombre){
		miEJB.addTarea(nombre);
	}
	
	public List<Tarea> getListaTareas(){
		return miEJB.getListaTareas();
	}
	
	public void borrarTarea(int id){
		miEJB.borrarTarea(id);
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
}

