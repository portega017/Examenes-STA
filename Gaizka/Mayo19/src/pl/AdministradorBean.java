package pl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Examen;

@Named
@ViewScoped
public class AdministradorBean implements Serializable {

	@EJB
	LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	private List<String> listaAulas = new ArrayList<String>();
	private String resp;
	private boolean render;

	public void altaAula(String s) {
		listaAulas.add(s);
	}
	
	public void altaRespuesta() {
		ln.altaResp();
	}
	
	public void altaExamen(Examen ex) {
		setResp(ln.altaExamen(ex,listaAulas));
		setRender(true);
		listaAulas = new ArrayList<String>();
	}

	public List<String> getListaAulas() {
		return listaAulas;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}
}
