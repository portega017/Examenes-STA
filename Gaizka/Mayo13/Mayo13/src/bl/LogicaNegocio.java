package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Contacto;

@Stateless
@LocalBean
public class LogicaNegocio {

	@PersistenceContext
	private EntityManager em;
	
	public void altaContacto(Contacto c) {
		if(c != null) {
			if(!em.createNamedQuery("Contacto.findByName").setParameter("nombre", c.getNombre()).getResultList().isEmpty()) {
				em.persist(c);
			}
		}
	}
	
	public void bajaContacto(int idProducto) {
		Contacto c = em.find(Contacto.class, idProducto);
		if(c != null) {
			em.remove(c);
		}
	}
	
	public Contacto buscarContacto(String nombre) {
		Contacto c = em.createNamedQuery("Contacto.findByNombre",Contacto.class).getSingleResult();
		if(c == null) {
			c = new Contacto();
		}
		return c;
	}
	
	public List<Contacto> getContactos(){
		return em.createNamedQuery("Contacto.findAll", Contacto.class).getResultList();
	}
	
}
