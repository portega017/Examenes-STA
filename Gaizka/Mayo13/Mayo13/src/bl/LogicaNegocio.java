package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Contacto;
import pl.modelo.ContactBean;

@Stateless
@LocalBean
public class LogicaNegocio {

	@PersistenceContext
	private EntityManager em;
	
	public void altaContacto() {
		
	}
	
	public void bajaContacto() {
		
	}
	
	public Contacto buscarContacto() {
		
	}
	
	public List<Contacto> getContactos(){
		return em.createNamedQuery("Contacto.findAll", Contacto.class).getResultList();
	}
	
}
