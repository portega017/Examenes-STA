package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Tarea;

@Named
@RequestScoped
public class TareaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarea tarea = new Tarea();
	private int codError;
	private String respAlta;
	private String respBaja;
	private boolean respRenderA=false;
	private boolean respRenderB=false;
	public TareaBean() {
		// TODO Auto-generated constructor stub
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
	@EJB
	private Servicios miEjb = new Servicios();
	
	public void altaTarea() {
		setCodError(miEjb.altaTarea(tarea));
		if(getCodError()==0) {
			setRespAlta("Tarea dada de alta correctamente.");
		}else if (getCodError()==2) {
			setRespAlta("La tarea ya existe.");
		}
		setRespRenderA(true);
		
	}
	public void bajaTarea(Tarea t) {
		setCodError(miEjb.borrarTarea(t.getIdTareas()));
		if(getCodError()==0) {
			setRespBaja("La tarea no existe");
		}else if (getCodError()==2) {
			setRespBaja("Tarea borrada Correctamente");
		}
		setRespRenderB(true);
	}
	public int getCodError() {
		return codError;
	}
	public void setCodError(int codError) {
		this.codError = codError;
	}
	public String getRespAlta() {
		return respAlta;
	}
	public void setRespAlta(String resp) {
		this.respAlta = resp;
	}
	public boolean isRespRenderA() {
		return respRenderA;
	}
	public void setRespRenderA(boolean respRender) {
		this.respRenderA = respRender;
	}
	public String getRespBaja() {
		return respBaja;
	}
	public void setRespBaja(String respBaja) {
		this.respBaja = respBaja;
	}
	public boolean isRespRenderB() {
		return respRenderB;
	}
	public void setRespRenderB(boolean respRenderB) {
		this.respRenderB = respRenderB;
	}
	public List<Tarea> getListaTareas(){
		return miEjb.getListadoTareas();
		
	}

}
