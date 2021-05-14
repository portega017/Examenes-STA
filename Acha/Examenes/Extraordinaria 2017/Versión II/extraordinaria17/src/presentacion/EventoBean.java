package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Evento;
import datos.Voto;
import negocio.Servicios;

@Named
@RequestScoped
public class EventoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Evento evento = new Evento();
	private int cont1=0;
	private int cont2=0;
	private int cont3=0;
	private boolean mostrarRecuento=false;
	
	public int getCont1() {
		return cont1;
	}

	public void setCont1(int cont1) {
		this.cont1 = cont1;
	}

	public int getCont2() {
		return cont2;
	}

	public void setCont2(int cont2) {
		this.cont2 = cont2;
	}

	public int getCont3() {
		return cont3;
	}

	public void setCont3(int cont3) {
		this.cont3 = cont3;
	}


	
	@EJB
	private Servicios miEJB;

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getListaeventos() {
		return miEJB.getLisaEventos();
	}

	public void borrarEvento(int idEvento) {
		miEJB.borrarEvento(idEvento);
	}

	public void addEvento() {
		evento.setEstado(false);
		miEJB.addEvento(evento);
	}

	public String mostrarMiEstado(boolean estado) {
		String stado;
		if (estado == false) {
			stado = "Abierto";
		} else {
			stado = "Cerrado";

		}
		return stado;
	}
	
	public void recuentoDias(int idEvento){
		List<Voto> misEventos	=new ArrayList<>();
		misEventos=miEJB.getLisaEventosRECUENTO(idEvento);
		for(Voto v :misEventos){
			if(v.getDia().equals("Viernes")){
				cont1++;
			}
			if(v.getDia().equals("Sabado")){
				cont2++;
			}
			if(v.getDia().equals("Sabado")){
				cont3++;
			}

		}
		mostrarRecuento=true;
		
	}

	public boolean isMostrarRecuento() {
		return mostrarRecuento;
	}

	public void setMostrarRecuento(boolean mostrarRecuento) {
		this.mostrarRecuento = mostrarRecuento;
	}
}
