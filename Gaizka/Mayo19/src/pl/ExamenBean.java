package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Examen;

@Named
@RequestScoped
public class ExamenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Examen entity = new Examen();

	public Examen getEntity() {
		return entity;
	}
}
