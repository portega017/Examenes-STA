package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import bl.Servicios;
import dl.Municipio;
import dl.Persona;

@Named
@ViewScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEjb = new Servicios();
	private List<Persona> lpt;
	private List<Municipio> municip;
	private float temp = 37;
	private boolean mostrarTabla = false;

	public List<Persona> getLpt() {
		if (lpt == null) {
			lpt = miEjb.getPersonasTemp(temp);
		}
		return lpt;
	}

	public List<Municipio> getMunicip() {
		if (municip == null) {
			municip = miEjb.getMunicipios();
		}
		return municip;
	}

	public void setLpt(List<Persona> lpt) {
		this.lpt = lpt;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public void pulsarBotonActualizar() {
		setMostrarTabla(true);
		lpt = null;
	}

	public String fromBoolToText(boolean bol) {

		if (bol == true) {
			return "Sí";
		} else {
			return "No";
		}

	}

	public int getAvisados(int idMun) {
		return miEjb.getInfectados(idMun);
	}
	public float getPorcentaje(int idMun) {
		return miEjb.getPorcentaje(idMun);
	}

	public void avisar() {
		miEjb.avisar(temp);
		lpt = null;
	}

	public void setMunicip(List<Municipio> municip) {
		this.municip = municip;
	}

}
