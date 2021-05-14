package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import datos.Evento;
import datos.Usuario;
import datos.Voto;

@LocalBean
@Stateless
@Path("servicios")
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Evento> getLisaEventos(){
		List<Evento> misEventos=new ArrayList<>();
		try{
			misEventos=em.createNamedQuery("Evento.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		
		return misEventos;
	}
	
	public void borrarEvento( int idEvento){
		Evento evento=em.find(Evento.class, idEvento);
		if(evento!=null){
			em.remove(evento);
		}
		
	}
	
	public void addEvento(Evento evento){
		em.persist(evento);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getListaEventosABIERTOS(){
		List<Evento> listaEvento = new ArrayList<>();
		try{
			listaEvento= em.createNamedQuery("Evento.findEstado").setParameter("estado", false).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return listaEvento;
	}
	

	@SuppressWarnings("unchecked")
	public List<Voto> getLisaEventosRECUENTO(int idEvento){
		Evento evento= em.find(Evento.class,idEvento);
		List<Voto> misVotos= new ArrayList<>();
		if(evento!=null){
		misVotos=em.createNamedQuery("Voto.findEVENTO").setParameter("evento", evento).getResultList();}
		return misVotos;
	}
	
	public Usuario getUsuario(String dni){
		return em.find(Usuario.class, dni);
	}
	public Evento getEvento(int idEvento){
		 return em.find(Evento.class, idEvento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Voto> getListaVotosUSUARIOEVENTO(Usuario usuario,Evento ev){
		List<Voto> misVotos = new ArrayList<>();
		misVotos=em.createNamedQuery("Voto.findUSUARIOEVENTO").setParameter("usuario", usuario).setParameter("evento", ev).getResultList();
		return misVotos;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("addVoto")
	public void addVoto(Voto v){
		em.persist(v);
	}
	

	
	
	
	
	

}
