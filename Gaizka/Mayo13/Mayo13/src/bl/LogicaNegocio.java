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
 
	private final int OK = 0;
	private final int Exists = 1;
	private final int NullParameter = 2;
	private final int OK2 = 3;
	private final int NullParameter2 = 4;

	
	@PersistenceContext
	private EntityManager em;
	
	public int altaContacto(Contacto c) {
		if(c != null) {
			if(em.createNamedQuery("Contacto.findByName").setParameter("nombre", c.getNombre()).getResultList().isEmpty()) {
				em.persist(c);
				return OK;
			}else {
				return Exists;
			}
		}else {
			return NullParameter;
		}
	}
	
	public int bajaContacto(int idProducto) {
		Contacto c = (Contacto) em.createNamedQuery("Contacto.findById").setParameter("id", idProducto).getSingleResult();
		if(c != null) {
			em.remove(c);
			return OK2;
		}else {
			return NullParameter2;
		}
	}
	
	public Contacto buscarContacto(String nombre) {
		Contacto c = em.createNamedQuery("Contacto.findByName",Contacto.class).setParameter("nombre", nombre).getSingleResult();
		//if(c == null) {
			//c = new Contacto();
		//}
		return c;
	}
	
	public List<Contacto> getContactos(){
		return em.createNamedQuery("Contacto.findAll", Contacto.class).getResultList();
	}
	
}
