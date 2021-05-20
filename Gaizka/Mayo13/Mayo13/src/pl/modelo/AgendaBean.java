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
	private int CodError;
	
	public void altaContacto(Contacto c) {
		setCodError(ln.altaContacto(c));
		lc = null;
	}
	
	public void bajaContacto(Contacto c) {
		setCodError(ln.bajaContacto(c.getIdCONTACTOS()));
		lc = null;
	}

	public Contacto buscarContacto(String nombre) {
		 return ln.buscarContacto(nombre);
	}
	
	public List<Contacto> getContactos() {
		if(lc == null) {
			lc = ln.getContactos();
		}
		return lc;
	}

	public int getCodError() {
		return CodError;
	}

	public void setCodError(int codError) {
		CodError = codError;
	}
}
