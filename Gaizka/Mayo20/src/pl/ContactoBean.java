package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Contacto;

@Named
@RequestScoped
public class ContactoBean implements Serializable{

	private final Contacto entity = new Contacto();
	private static final long serialVersionUID = 1L;
	
	public Contacto getEntity() {
		return entity;
	}
}