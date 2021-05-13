package pl.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Contacto;

@Named
@ViewScoped
public class AgendaBean implements Serializable {

	@EJB
	private LogicaNegocio ln = new LogicaNegocio();
	private List<Contacto> lc = new ArrayList<Contacto>();
	private static final long serialVersionUID = 1L;
	
	public void altaContacto(Contacto c) {
		ln.altaContacto(c);
	}
	
	public void bajaContacto(Contacto c) {
		ln.bajaContacto(c.getIdCONTACTOS());
	}

	public Contacto buscarContacto() {
		Contacto c = ln.buscarContacto(nombreBuscar);
		
		return c;
	}
	
	public List<Contacto> getContactos() {
		if(lc == null) {
			lc = ln.getContactos();
		}
		return lc;
	}

	public String getNombreBuscar() {
		return nombreBuscar;
	}

	public void setNombreBuscar(String nombreBuscar) {
		this.nombreBuscar = nombreBuscar;
	}
}
