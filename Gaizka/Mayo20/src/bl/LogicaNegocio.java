package bl;

import java.text.DecimalFormat;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Contacto;
import dl.Municipio;
import dl.Persona;

@Stateless
@LocalBean
public class LogicaNegocio {

	@PersistenceContext
	private EntityManager em;
	
	
	public void avisar(float temp) {
		List<Persona> lp = getPersonasTemp(temp);
		List<Contacto> contagiados = em.createNamedQuery("Contacto.findContagiados",Contacto.class).getResultList();

		for(Persona p: lp) { // Todos los que superen el umbral
			p.setAvisado(true);
			em.persist(p);
		}
		
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
	
	public List<Municipio> getMunicipios() {
		return em.createNamedQuery("Municipio.findAll", Municipio.class).getResultList();
	}
	
	public int getAfectados(int munId) {
		int afectados = 0;
		List<Persona> lm =  em.createNamedQuery("Persona.findByMunicipio",Persona.class).setParameter("munId", munId).getResultList();
		for(Persona p : lm) {
			if(p.getAvisado() == true) {
				afectados++;
			}
		}
		return afectados;
	}
	
	public float getPorAfectados(int munId) {
		float afectados = getAfectados(munId);
		float numHab = em.createNamedQuery("Persona.findByMunicipio",Persona.class).setParameter("munId", munId).getResultList().size();

		return (afectados / numHab) * 100;
	}
}





