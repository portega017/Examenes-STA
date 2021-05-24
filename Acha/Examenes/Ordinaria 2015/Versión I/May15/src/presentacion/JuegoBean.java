package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Juego;
import negocio.Servicios;
@RequestScoped
@Named
public class JuegoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Juego juego = new Juego();
	private boolean ok=false;
	
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	public List<Juego> getListaJuegos(){
		return miEJB.getListaJuego();
	}
	
	public void borrarJuego(int id){
		miEJB.borrarJUEGO(id);
	}
	
	public List<Juego> getListaPartido(){
		List<Juego> miLista= new ArrayList<Juego>();
		miLista=miEJB.getPartidos(juego.getAnio(), juego.getSemana());
		if(miLista.isEmpty()==false){
			ok=true;
		}
		return miLista;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	public void addJuego(int id1,int id2){
		miEJB.addJuego(id1,id2,juego.getAnio(), juego.getSemana(), juego.getResultado());
	}
	
	
}
