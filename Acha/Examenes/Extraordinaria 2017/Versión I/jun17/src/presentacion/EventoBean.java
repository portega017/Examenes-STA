package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Evento;
import negocio.Servicios;

@Named
@RequestScoped
public class EventoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Evento e =new Evento();
	


	public Evento getE() {
		return e;
	}

	public void setE(Evento e) {
		this.e = e;
	}
	
	public void addEvento(){
		e.setEstado(false);
		miEJB.addEvento(e);
	}
	
	public void borrarEvento(int id){
		miEJB.borrarEvento(id);
	}

		
	public List<Evento> getListaEventos(){
		return miEJB.getListaEventos();
	}
	
	public String mostrarEstado(int id){
		String estado=new String();
		e=miEJB.findEvento(id);
		if(e!=null){
			if(e.getEstado()==true){
				estado="cerrado";
			}else{
				estado="abierto";
			}
		}
		return estado;
	}
	
	public List<Evento> getListadoEventoESTADO(){
		return miEJB.getListaEventoESTADO();
	}

	

}
