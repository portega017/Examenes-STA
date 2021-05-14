package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Asistencia;
import datos.Evento;
import negocio.Servicios;

@Named
@SessionScoped
public class AsistenciaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Asistencia viernes = new Asistencia();
	private Asistencia sabado = new Asistencia();
	private Asistencia domingo = new Asistencia();
	private boolean mostrar=false;
	private boolean tabla =true;
	@EJB
	private Servicios miEJB;

	
	
	public void seleccionar(Evento evento){
		viernes.setEvento(evento);
		sabado.setEvento(evento);
		domingo.setEvento(evento);
		setTabla(false);
		setMostrar(true);
	}
	
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	public boolean isTabla() {
		return tabla;
	}
	public void setTabla(boolean tabla) {
		this.tabla = tabla;
	}
	public Asistencia getViernes() {
		return viernes;
	}
	public void setViernes(Asistencia viernes) {
		this.viernes = viernes;
	}
	public Asistencia getSabado() {
		return sabado;
	}
	public void setSabado(Asistencia sabado) {
		this.sabado = sabado;
	}
	public Asistencia getDomingo() {
		return domingo;
	}
	public void setDomingo(Asistencia domingo) {
		this.domingo = domingo;
	}
	
	public void reiniciarAsistencia(){
		viernes = new Asistencia();
		sabado = new Asistencia();
		domingo = new Asistencia();
	}
	
}
