package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Nota;
import negocio.Servicios;

@RequestScoped
@Named
public class NotaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nota nota = new Nota();
	@EJB
	private Servicios miEJB;
	private boolean mostrar = false;
	private boolean correcto = false;	
	private boolean addCorrecto = false;

	public List<Nota> obtenerNotas(String dni) {
		List<Nota> lista = new ArrayList<Nota>();
		if (dni.length() == 9 && dni != null) {
			lista = miEJB.obtenerNotas(dni);
			if (lista.size() != 0) {
				mostrar = true;
			}
		} else {
			setCorrecto(false);
		}
		return lista;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public boolean isCorrecto() {
		return correcto;
	}

	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}
	public boolean addNota(String dni,String idAsignatuara,int numero){
		addCorrecto=miEJB.addNota(dni, idAsignatuara, numero);
		
		return addCorrecto;
	}
	
	public List<Nota> obtenerNotasTOTALES(){
		return miEJB.obtenerNotasTotaltes();
	}
	
	public void borrarNota(int idNota){
		miEJB.borrarNota(idNota);
	}
	
	
	public boolean isAddCorrecto() {
		return addCorrecto;
	}

	public void setAddCorrecto(boolean b) {
		addCorrecto = b;
	}
	

}
