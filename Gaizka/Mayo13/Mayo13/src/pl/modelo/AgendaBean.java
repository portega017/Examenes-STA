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
	private boolean renderLista=false;
	private boolean renderBuscar=false;
	private String error;
	private String buscar;
	private Contacto buscado;
	
	public void altaContacto(Contacto c) {
		setCodError(ln.altaContacto(c));
		if(getCodError() == 0) {
			setError("Contacto dado de alta satisfactoriamente");
		}else if(getCodError() == 1) {
			setError("El contacto que esta intentado añadir ya existe");
		}
		lc = null;
	}
	
	public void bajaContacto(Contacto c) {
		setCodError(ln.bajaContacto(c.getIdCONTACTOS()));
		lc = null;
	}

	public void buscarContacto(String nombre) {
		Contacto c = ln.buscarContacto(nombre);
		if(c != null) {
			setBuscado(c);
		}
		setRenderBuscar(true);
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

	public boolean isRenderLista() {
		return renderLista;
	}

	public void setRenderLista(boolean render) {
		this.renderLista = render;
	}
	
	public void setRenderTrue() {
		lc = null;
		setRenderLista(true);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isRenderBuscar() {
		return renderBuscar;
	}

	public void setRenderBuscar(boolean renderBuscar) {
		this.renderBuscar = renderBuscar;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public Contacto getBuscado() {
		return buscado;
	}

	public void setBuscado(Contacto buscado) {
		this.buscado = buscado;
	}
	
}
