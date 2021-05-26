package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Municipio;

@Named
@RequestScoped
public class MunicipioBean implements Serializable{

	private final Municipio entity = new Municipio();
	private static final long serialVersionUID = 1L;
	
	public Municipio getEntity() {
		return entity;
	}
}