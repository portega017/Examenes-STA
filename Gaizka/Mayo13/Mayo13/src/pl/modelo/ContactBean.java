package pl.modelo;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dl.Contacto;

@Named
@SessionScoped
public class ContactBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final Contacto entity = new Contacto();

	public Contacto getEntity() {
		return entity;
	}
	
}

