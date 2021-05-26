package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Persona;

@Named
@RequestScoped
public class PersonaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Persona entity = new Persona();
	public Persona getEntity() {
		return entity;
	}

}
