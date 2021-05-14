package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ApuestaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resultado;
	private String apuesta;
	private String equipo1;
	private String equipo2;
	private int anio;
	private int semana;
	private int id;

	public ApuestaMB() {
		// TODO Auto-generated constructor stub
	}

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getApuesta() {
		return apuesta;
	}

	public void setApuesta(String apuesta) {
		this.apuesta = apuesta;
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

}
