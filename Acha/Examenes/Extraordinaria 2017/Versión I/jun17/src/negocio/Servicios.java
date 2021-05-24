package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Asistencia;
import datos.Evento;
import datos.Usuario;
import presentacion.AsistenciaBean;

@Stateless
@LocalBean
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	public void borrarEvento(int id){
		Evento e= em.find(Evento.class, id);
		if(e!=null){
		em.remove(e);	
		}
	}
	
	public void addEvento(Evento e){
		em.persist(e);
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getListaEventos(){
		List<Evento> miLista =new ArrayList<Evento>();
		try{
			miLista=em.createNamedQuery("Evento.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public Evento findEvento(int id){
		return em.find(Evento.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getListaEventoESTADO(){
		List<Evento> miLista = new ArrayList<Evento>();
		miLista = em.createNamedQuery("Evento.findEstado").setParameter("estado", false).getResultList();
		return miLista;
	}
	
	@SuppressWarnings("unchecked")
	public int participar(String nomUsuario, String contraseña,AsistenciaBean asistencia){
		int i=0;
		Usuario u=new Usuario();
		List<Asistencia> miLista = new ArrayList<Asistencia>();
		asistencia.getViernes().setDia("Viernes");
		asistencia.getSabado().setDia("Sabado");
		asistencia.getDomingo().setDia("Domingo");
		try {
			u=(Usuario) em.createNamedQuery("Usuario.findUsuario").setParameter("usuario", nomUsuario).getSingleResult();
			if(u.getContraseña().equals(contraseña)){
				miLista=em.createNamedQuery("Asistencia.findEVENTOPERSONA").setParameter("usuario", nomUsuario).setParameter("evento", asistencia.getViernes().getEvento().getNombreEvento()).getResultList();     
				if(miLista.isEmpty()==true){
				asistencia.getViernes().setUsuario(u);
				asistencia.getSabado().setUsuario(u);
				asistencia.getDomingo().setUsuario(u);
				if(asistencia.getViernes().getDisponibilidad()==true){
					em.persist(asistencia.getViernes());	
				}
				if(asistencia.getSabado().getDisponibilidad()==true){
					em.persist(asistencia.getSabado());
				}
				if(asistencia.getDomingo().getDisponibilidad()==true){
				em.persist(asistencia.getDomingo());
				}
				
				i=1;
				}else{
					i=2;
				}
			
			}else{
				i=3;
			}
		} catch (NoResultException e) {
			u.setNombreUsuario(nomUsuario);
			u.setContraseña(contraseña);
			em.persist(u);

			asistencia.getViernes().setUsuario(u);
			asistencia.getSabado().setUsuario(u);
			asistencia.getDomingo().setUsuario(u);

			em.persist(asistencia.getViernes());
			em.persist(asistencia.getSabado());
			em.persist(asistencia.getDomingo());
			
			i=0;
			// TODO: handle exception
		}
		
		
		return i;
	
	}

	@SuppressWarnings("unchecked")
	public List<Integer> cerrarEvento(int idEvento){
		Evento evento=new Evento();
		evento=em.find(Evento.class, idEvento);
		evento.setEstado(true);
		em.persist(evento);
		List<Integer> contadores =new ArrayList<Integer>();
		int cont0=0;
		int cont1=0;
		int cont2=0;
		
		List<Asistencia> miLista=new ArrayList<Asistencia>();
		miLista=em.createNamedQuery("Asistencia.findEVENTO").setParameter("evento", evento.getNombreEvento()).getResultList();
		for(int i=0;i<miLista.size();i++){
			if(miLista.get(i).getDia().equals("Viernes")){
				cont0=cont0+1;
			}
			
			if(miLista.get(i).getDia().equals("Sabado")){
				cont1=cont1+1;
			}
			
			if(miLista.get(i).getDia().equals("Domingo")){
				cont2=cont2+1;
			}
		}
		contadores.add(cont0);
		contadores.add(cont1);
		contadores.add(cont2);
		return contadores;
	}





}
