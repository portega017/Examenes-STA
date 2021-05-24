package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import datos.Equipo;
import datos.Juego;
import negocio.Servicios;
@Named
@RequestScoped
public class JuegoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Juego juego = new Juego();

	private List<SelectItem> items;
	private List<Equipo> listaequipos;
	private int indice1;
	private int indice2;
	
	
	
	public List<Juego> getListaJuegos(){
		return miEJB.getListaLuegos();
	}
	
	public void borrarJuego(int id){
		miEJB.borrarJuego(id);
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}


	public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}






	public int getIndice1() {
		return indice1;
	}

	public void setIndice1(int indice1) {
		this.indice1 = indice1;
		juego.setEquipo1Bean(listaequipos.get(indice1));
	
	}

	public int getIndice2() {
		return indice2;
	}

	public void setIndice2(int indice2) {
		this.indice2 = indice2;
		juego.setEquipo2Bean(listaequipos.get(indice2));
	}
	
	@PostConstruct
	public void prepararListaItems(){
		listaequipos=miEJB.getListaEQUIPOS();
		items=new ArrayList<>(listaequipos.size());
		
		for(Equipo e :listaequipos){
			items.add(new SelectItem(indice1++,e.getNombre()));
		}
		
		indice1=0;
		indice2=0;
	}
	
	public void addJuego(){
		miEJB.addJuego(juego);
	}
	
	
}
