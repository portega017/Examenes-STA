package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Entrega;

@Named
@RequestScoped
public class EntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Entrega entity = new Entrega();

	public Entrega getEntity() {
		return entity;
	}
}
