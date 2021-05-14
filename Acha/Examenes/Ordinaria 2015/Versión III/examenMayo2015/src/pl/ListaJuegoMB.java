package pl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.LogicaEJB;
import dl.Juego;

@Named
@SessionScoped
public class ListaJuegoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int anio;
	private int semana;
	private boolean mostrar = false;
	private boolean mostrarApuesta = false;
	private List<ApuestaMB> listaCompleta;

	private int acierto = 0;
	
	@EJB
	private LogicaEJB ejb;

	public ListaJuegoMB() {
		// TODO Auto-generated constructor stub
	}
	

	public List<ApuestaMB> getListaCompleta() {
		return listaCompleta;
	}


	public void setListaCompleta(List<ApuestaMB> listaCompleta) {
		this.listaCompleta = listaCompleta;
	}


	public int getAcierto() {
		return acierto;
	}


	public void setAcierto(int acierto) {
		this.acierto = acierto;
	}


	public boolean isMostrarApuesta() {
		return mostrarApuesta;
	}


	public void setMostrarApuesta(boolean mostrarApuesta) {
		this.mostrarApuesta = mostrarApuesta;
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

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public List<Juego> getLista()
	{
		List<Juego> lista = ejb.getListaJuego();
		
		return lista;
	}
	
	public void buscar()
	{
		setMostrar(true);
	}
	
	public List<Juego> getListaConcreta()
	{
		List<Juego> lista = ejb.getListaJuegoConcreta(anio, semana);
		
		return lista;
	}
	public List<ApuestaMB> getlistaApuestaMB()
	{
		listaCompleta = new ArrayList<ApuestaMB>();
		List<Juego> lista = getListaConcreta();
		for(int i=0; i< lista.size(); i++)
		{
			ApuestaMB apuesta = new ApuestaMB();
			apuesta.setAnio(lista.get(i).getAnio());
			apuesta.setEquipo1(lista.get(i).getEquipo1Bean().getNombre());
			apuesta.setEquipo2(lista.get(i).getEquipo2Bean().getNombre());
			apuesta.setId(lista.get(i).getId());
			apuesta.setResultado(lista.get(i).getResultado());
			apuesta.setSemana(lista.get(i).getSemana());
			
			listaCompleta.add(apuesta);
		}
		
		return listaCompleta;
	}
	
	public void validar()
	{
		acierto =0;
		for(int i=0; i<listaCompleta.size(); i++)
		{
			if(listaCompleta.get(i).getResultado().equals(listaCompleta.get(i).getApuesta()))
			{
				acierto++;
			}
		}
		setMostrarApuesta(true);
	}
	


}
