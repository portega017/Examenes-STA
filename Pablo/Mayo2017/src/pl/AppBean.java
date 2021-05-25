package pl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Tarea;

@Named
@SessionScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	@EJB
	private Servicios miEjb = new Servicios();

	private static final long serialVersionUID = 1L;

	public AppBean() {
		// TODO Auto-generated constructor stub
	}
	
	


	
}
