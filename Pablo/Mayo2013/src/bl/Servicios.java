package bl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dl.Contacto;

@Stateless
@LocalBean


public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Contacto> getListado(){
		List <Contacto> misContactos = new ArrayList<>();
		try {
		misContactos=em.createNamedQuery("Contacto.findAll").getResultList();
		}catch(NoResultException e) {
			
		}
		return misContactos;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Contacto> getListaPorNombre(String nombre){
		List <Contacto> misContactos = new ArrayList<>();
		
		try {
			misContactos=em.createNamedQuery("Contacto.findNombre").setParameter("nombre", nombre).getResultList();
		}catch(NoResultException e) {
			
		}
		
		return misContactos;
	}
	
	
	public void addContact(Contacto c) {
		em.persist(c);
	}
	
	public void borrarContact(int id) {
		Contacto c = new Contacto();
		c=em.find(Contacto.class, id);
		em.remove(c);
	}
	
	
}
