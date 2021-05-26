package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Reserva;

@Named
@RequestScoped
public class ReservaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Reserva entity = new Reserva();

	public Reserva getEntity() {
		return entity;
	}
}
