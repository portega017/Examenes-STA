package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Contacto;



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
	public List<Contacto> getListaContactosENTERA(){
		List<Contacto> misContactos = new ArrayList<>();
		try{
			misContactos=em.createNamedQuery("Contacto.findAll").getResultList();			
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misContactos;
	}
	
	
	
	public void addContacto(Contacto c){
		em.persist(c);
		
	}
	
	
	public void borrarContacto(int id){
		Contacto c = new Contacto();
		c=em.find(Contacto.class,id);
		em.remove(c);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Contacto>  getListaContactosPARCIAL(String nombre){
		List<Contacto> misContactos = new ArrayList<>();
		try{
			misContactos=em.createNamedQuery("Contacto.finPorNombre").setParameter("nombre", nombre).getResultList();			
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misContactos;
	}
}
