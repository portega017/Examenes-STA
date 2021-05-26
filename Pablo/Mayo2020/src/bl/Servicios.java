package bl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Contacto;
import dl.Municipio;
import dl.Persona;

@LocalBean
@Stateless

public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager em;

	public List<Persona> getPersonas() {
		List<Persona> listado = new ArrayList<Persona>();
		try {
			listado = em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
		} catch (Exception ex) {
			ex.getCause();
		}
		return listado;
	}

	public int getInfectados(int idMun) {
		int infectados = 0;
		List<Persona> list = em.createNamedQuery("Persona.findByMunicipio", Persona.class).setParameter("idMun", idMun)
				.getResultList();
		for (Persona pers : list) {
			if (pers.getAvisado() == true) {
				infectados++;
			}
		}
		return infectados;
	}

	public List<Municipio> getMunicipios() {
		List<Municipio> listaMuni = new ArrayList<Municipio>();

		try {
			listaMuni = em.createNamedQuery("Municipio.findAll", Municipio.class).getResultList();
		} catch (Exception ex) {
			ex.getCause();
		}

		return listaMuni;
	}

	public List<Persona> getPersonasTemp(float temp) {
		List<Persona> listado = new ArrayList<Persona>();
		try {
			listado = em.createNamedQuery("Persona.findByTemp", Persona.class).setParameter("temp", temp)
					.getResultList();
		} catch (Exception ex) {
			ex.getCause();
		}
		return listado;
	}

	public void avisar(float temp) {
		List<Persona> lp = getPersonasTemp(temp);
		List<Contacto> contagiados = em.createNamedQuery("Contacto.findContagiado", Contacto.class).getResultList();

		for (Persona pers : lp) {
			pers.setAvisado(true);
			em.persist(pers);
		}
		for (Persona pers : lp) {
			for (Contacto cont : contagiados) {
				if (pers.getId() == cont.getPersona1().getId() || pers.getId() == cont.getPersona2().getId()) {
					pers.setAvisado(true);
					em.persist(pers);
				}
			}
		}
	}

	public float getPorcentaje(float infectados, float habitantes) {
	
		float porcent=((infectados / habitantes) * 100);
		return porcent;
	}
}
