package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Examen;

@Named
@ViewScoped
public class ProfesorBean implements Serializable {

	@EJB
	LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	List<Examen> listaExamenes;
	
	
	public List<Examen> getListaExamenes() {
		if(listaExamenes == null)
			listaExamenes = ln.getExamenes();
		return listaExamenes;
	}
}
