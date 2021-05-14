package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import negocio.Servicios;

@Named
@SessionScoped
public class VotacionesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> miLista=new ArrayList<Integer>();
	private boolean mostrar1=false;

	
	@EJB
	private Servicios miEJB;
	
	public void contarVotaciones(int idEvento){
		miLista=miEJB.cerrarEvento(idEvento);
		mostrar1=true;
		
	}

	public List<Integer> getMiLista() {
		return miLista;
	}

	public void setMiLista(List<Integer> miLista) {
		this.miLista = miLista;
	}

	public boolean isMostrar1() {
		return mostrar1;
	}

	public void setMostrar1(boolean mostrar1) {
		this.mostrar1 = mostrar1;
	}


	

}
