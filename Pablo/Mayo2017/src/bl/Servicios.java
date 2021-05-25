package bl;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import dl.Tarea;



@Stateless
@LocalBean

public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	
	public void addTarea(String nomTarea) {
		Tarea tarea  = new Tarea();
		tarea.setNombreTareas(nomTarea);
		em.persist(tarea);
	}

}
