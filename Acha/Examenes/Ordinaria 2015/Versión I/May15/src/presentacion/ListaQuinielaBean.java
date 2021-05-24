package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Juego;
import negocio.Servicios;

@SessionScoped
@Named
public class ListaQuinielaBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<QuinielaBean> miQuiniela= new ArrayList<QuinielaBean>();
	private Juego juego=new Juego();
	private int con=0;
	private boolean ok=false;
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}

	private boolean mostrar=false;
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	@EJB
	private Servicios miEJB;

	/*public void addJuego(int id1,int id2){
		miEJB.addJuego(id1,id2,juego.getAnio(), juego.getSemana(), juego.getResultado());
	}*/

	public List<QuinielaBean> getMiQuiniela() {
		return miQuiniela;
	}
	public void setMiQuiniela(List<QuinielaBean> miQuiniela) {
		this.miQuiniela = miQuiniela;
	}
	
	public List<QuinielaBean> getPartidosTOTALES(){
		System.out.println("HOLAAAA");
		List<Juego> miLista= new ArrayList<Juego>();
		miLista=miEJB.getPartidos(juego.getAnio(), juego.getSemana());
		if(miLista.isEmpty()==false){
			ok=true;
		}
		for(int i=0;i<miLista.size();i++){
			QuinielaBean q=new QuinielaBean();
			q.setJ(miLista.get(i));
			miQuiniela.add(q);
	

		}
		return miQuiniela;
	}
	
	public boolean comparar(){
		con=0;
		for(QuinielaBean q:miQuiniela){
			if(q.getApuesta()==q.getJ().getResultado()){
				con= con+1;
			}
		}
		mostrar=true;
		
		return mostrar;
	}
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
