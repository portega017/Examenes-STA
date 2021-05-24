package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Juego;
import negocio.Servicios;


@SessionScoped
@Named
public class QuinielaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Juego j = new Juego();
	private char apuesta;



	public Juego getJ() {
		return j;
	}

	public void setJ(Juego j) {
		this.j = j;
	}

	public char getApuesta() {
		return apuesta;
	}

	public void setApuesta(char apuesta) {
		this.apuesta = apuesta;
	}
	
}
