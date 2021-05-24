package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Contacto;

@Stateless
@LocalBean
public class Servicios {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Contacto> getListaContactos(){
		List<Contacto> miLista = new ArrayList<Contacto>();
		try{
			miLista=em.createNamedQuery("Contacto.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public void deleteContacto(int id){
		Contacto contacto = em.find(Contacto.class,id);
		if(contacto!=null){
			em.remove(contacto);
		}
	}
	
	public void addContacto(Contacto contacto){
		if(contacto.getNombre()!=null){
		em.persist(contacto);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Contacto> getContactoNOMBRE(String nombre){
		List<Contacto> cont = new ArrayList<Contacto>();
		try{
			cont = em.createNamedQuery("Contacto.findNombre").setParameter("nombre", nombre).getResultList();
		}catch(NoResultException e){
			
		}
		return cont;
	}
}
