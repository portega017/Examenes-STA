package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Equipo;
import negocio.Servicios;

@Named
@RequestScoped
public class EquipoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equipo e1=new Equipo();
	private Equipo e2=new Equipo();
	@EJB
	private Servicios miEJB;
	public Equipo getE1() {
		return e1;
	}
	public void setE1(Equipo e1) {
		this.e1 = e1;
	}
	public Equipo getE2() {
		return e2;
	}
	public void setE2(Equipo e2) {
		this.e2 = e2;
	}
}
