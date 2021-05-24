package pl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import bl.LogicaEJB;

@Named
@RequestScoped
public class JuegoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int anio;
	private int semana;
	private String resultado;
	
	private String equipo1;
	private String equipo2;
	
	@EJB
	private LogicaEJB ejb;

	public JuegoMB() {
		// TODO Auto-generated constructor stub
	}
	
	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public void eliminar(int id)
	{
		ejb.eliminarJuego(id);
	}
	
	public void add()
	{
		ejb.addJuego(anio, semana, resultado, equipo1, equipo2);
	}

}
