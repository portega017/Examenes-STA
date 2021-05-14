package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Juego;
import negocio.Servicios;
@Named
@RequestScoped
public class ConsultaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private int semana;
	private int anio;
 
	private boolean mostrarPartidos=false;
	
	public List<Juego> getListaSemanaAnio(){
		List<Juego> misJuegos = miEJB.getListaAnioSemana(semana, anio);
		if(misJuegos.isEmpty()==false){
			mostrarPartidos=true;
		}
		return misJuegos;
	}

	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public boolean isMostrarPartidos() {
		return mostrarPartidos;
	}

	public void setMostrarPartidos(boolean mostrarPartidos) {
		this.mostrarPartidos = mostrarPartidos;
	}

}
