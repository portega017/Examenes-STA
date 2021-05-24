package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


import datos.Asignatura;
import negocio.Servicios;

@Named
@RequestScoped
public class AsignaturaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asignatura asignatura=new Asignatura();
	private boolean correcto;

	@EJB
	private Servicios miEJB;

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	public List<Asignatura> getListaAsignatura(){
		return miEJB.getListaAsignatura();
	}
	
	public void borrarAsignatura(int id){
		miEJB.borrarAsignatura(id);
	}
	
	public void addAsignatura(){
		miEJB.addAsignatura(asignatura);
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	
	
}
