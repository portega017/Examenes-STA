package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Contacto;
import dl.Persona;

@Stateless
@LocalBean
public class LogicaNegocio {

	@PersistenceContext
	private EntityManager em;
	
	
	public void avisar(float temp) {
		List<Persona> lp = getPersonasTemp(temp);
		List<Contacto> contagiados = em.createNamedQuery("Contacto.findContagiados",Contacto.class).getResultList();

		for(Persona p: lp) {
			for(Contacto c : contagiados) {
				if(p.getId() == c.getPersona1().getId() || p.getId() == c.getPersona2().getId()) {
					p.setAvisado(true);
					em.persist(p);
				}
			}
		}
	}
	
	public List<Persona> getPersonas() {
		return em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
	}
	
	public List<Persona> getPersonasTemp(float temp) {
		return em.createNamedQuery("Persona.findByUpTemp", Persona.class).setParameter("temp", temp).getResultList();
	}
	
}
