package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Juego;
import negocio.Servicios;

@Named
@SessionScoped
public class QuinielaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Character> misResultados = new ArrayList<Character>() ;
	private char miResultado;
	private int semana;
	private int anio;
	int i;
	private boolean mostrarPartidos=false;

	@EJB
	private Servicios miEJB;
	public List<Character> getMisResultados() {
		return misResultados;
	}
	public void setMisResultados(List<Character> misResultados) {
		this.misResultados = misResultados;
	}
	
	public void getResultados(){
		List<Juego> misJuegos = miEJB.getListaAnioSemana(semana, anio);
		i=0;
		for(int k=0;k<misJuegos.size();k++){
			if(misJuegos.get(k).getResultado()==misResultados.get(k).charValue()){
				i++;
				}
		}

	}
	public char getMiResultado() {
		return miResultado;
	}
	public void setMiResultado(char miResultado) {
		this.miResultado = miResultado;
		misResultados.add(miResultado);
	}
	
	public List<Juego> getListaSemanaAnio(){
		List<Juego> misJuegos = miEJB.getListaAnioSemana(semana, anio);
		if(misJuegos.isEmpty()==false){
			setMostrarPartidos(true);
		}
		return misJuegos;
	}
	public boolean isMostrarPartidos() {
		return mostrarPartidos;
	}
	public void setMostrarPartidos(boolean mostrarPartidos) {
		this.mostrarPartidos = mostrarPartidos;
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
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	
	}
}
