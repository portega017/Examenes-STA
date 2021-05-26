package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Municipio;
import dl.Persona;

@Named
@ViewScoped
public class AppBean implements Serializable {

	@EJB
	LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	private float temp = 37;
	private List<Persona> lpt;
	private List<Municipio> lm;

	public void updateList() {
		lpt = null;
	}
	
	public List<Persona> getLpt() {
		if(lpt == null)
			lpt = ln.getPersonasTemp(temp);
		return lpt;
	}
	
	public List<Municipio> getLm() {
		if(lm == null)
			lm = ln.getMunicipios();
		return lm;
	}
	
	public void avisar() {
		ln.avisar(temp);
		lpt = null;
	}
	
	public String fromBoolToText(boolean bol) {
		if(bol == true) {
			return "Sí";
		}else {
			return "No";
		}
	}
	
	public int getAfectados(int munId) {
		return ln.getAfectados(munId);
	}
	
	public float getPorAfectados(int munId) {
		return ln.getPorAfectados(munId);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}
	
}

