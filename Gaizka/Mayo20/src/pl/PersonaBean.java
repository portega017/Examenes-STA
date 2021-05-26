package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Persona;

@Named
@RequestScoped
public class PersonaBean implements Serializable{

	private final Persona entity = new Persona();
	private static final long serialVersionUID = 1L;
	
	public Persona getEntity() {
		return entity;
	}
}
