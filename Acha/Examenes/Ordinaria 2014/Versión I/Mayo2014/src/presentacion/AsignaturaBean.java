package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Asignatura;
import negocio.Servicios;

@RequestScoped
@Named
public class AsignaturaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Asignatura asignatura = new Asignatura();

	@EJB
	private Servicios miEJB;

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void addAsignatura() {
		miEJB.addAsignatura(asignatura);
	}

	public void deleteAsignatura(int id) {
		miEJB.deleteAsignatura(id);
	}
	
	public List<Asignatura> getAsignaturas(){
		return miEJB.getAsignaturas();
	}
}
