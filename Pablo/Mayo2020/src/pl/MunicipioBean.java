package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Municipio;

@Named
@RequestScoped
public class MunicipioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Municipio entity = new Municipio();
	public MunicipioBean() {
		// TODO Auto-generated constructor stub
	}
	public Municipio getEntity() {
		return entity;
	}

}
