package pl;

import java.io.Serializable;
import java.util.HashSet;

import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Entrega;
import dl.Examen;


@Named
@SessionScoped
public class AlumnosBean implements Serializable {

	@EJB
	private LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	HashSet<Examen> listaExamenesAlumno;
	private int idxExamenes;
	private String resp;
	private boolean render = false;
	private boolean renderNombre = false;
	
	public HashSet<Examen> getListaExamenesAlumno(String nombre) {
		System.err.println("Nombre: " + nombre);
		if(listaExamenesAlumno == null)
			listaExamenesAlumno = ln.getExamenesAlumno(nombre);
		return listaExamenesAlumno;
	}
	
	public HashSet<Examen> getListaExamen(){
		return listaExamenesAlumno;
	}
	
	public void actualizaLista(String nombre) {
		listaExamenesAlumno = null;
	}
	
	public void altaRespuesta(Entrega e) {
		setResp(ln.altaResp(e, idxExamenes));
		System.err.println("Nombre entrega: " + e.getNombre() + "\nRespuesta: " + e.getRespuesta() + "\nAsignatura: " + e.getExamene().getAsignatura() + "\nAlumnos: " + e.getExamene().getAlumnos() + "\nIdxExam: " + idxExamenes);
		setRender(true);
		setRenderNombre(true);
		listaExamenesAlumno = null;
	}
	
	public void recibidoNombre(String nombre) {
		getListaExamenesAlumno(nombre);
		setRenderNombre(true);
	}

	public int getIdxExamenes() {
		return idxExamenes;
	}

	public void setIdxExamenes(int idxExamenes) {
		this.idxExamenes = idxExamenes;
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

	public boolean isRenderNombre() {
		return renderNombre;
	}
	
	public void setRenderNombre(boolean renderNombre) {
		this.renderNombre = renderNombre;
	}
}
