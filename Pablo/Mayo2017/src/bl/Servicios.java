package bl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import dl.Categoria;
import dl.Tarea;

@Stateless
@LocalBean
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	private final int OK = 0;
	private final int Exception = 1;
	private final int Exists = 2;

	public int altaTarea(Tarea t) {

		try {
			if (em.createNamedQuery("Tarea.findByName").setParameter("nombre", t.getNombreTareas()).getResultList()
					.isEmpty()) {
				em.persist(t);
				return OK;
			} else {
				return Exists;
			}
		} catch (Exception ex) {
			ex.getCause();
			return Exception;
		}
	}

	public int borrarTarea(int id) {

		try {
			Tarea t = (Tarea) em.createNamedQuery("Tarea.findById").setParameter("id", id).getSingleResult();
			if (t != null) {
				em.remove(t);
				return OK;
			} else {
				return Exists;
			}
		} catch (Exception ex) {
			ex.getCause();
			return Exception;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tarea> getListadoTareas() {
		List<Tarea> misTareas = new ArrayList<>();
		try {
			misTareas = em.createNamedQuery("Tarea.findAll").getResultList();
		} catch (NoResultException e) {

		}
		return misTareas;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getListadoCategorias() {
		List<Categoria> misCategorias = new ArrayList<>();
		try {
			misCategorias = em.createNamedQuery("Categoria.findAll").getResultList();
		} catch (NoResultException e) {

		}
		return misCategorias;
	}
	
	
	public int altaCategoria(Categoria c) {

		try {
			if (em.createNamedQuery("Categoria.findByName").setParameter("nombre", c.getNombreCategotria()).getResultList()
					.isEmpty()) {
				em.persist(c);
				return OK;
			} else {
				return Exists;
			}
		} catch (Exception ex) {
			ex.getCause();
			return Exception;
		}
	}

	public int borrarCategoria(int id) {

		try {
			Categoria c= (Categoria) em.createNamedQuery("Categoria.findById").setParameter("id", id).getSingleResult();
			if (c != null) {
				em.remove(c);
				return OK;
			} else {
				return Exists;
			}
		} catch (Exception ex) {
			ex.getCause();
			return Exception;
		}
	}
	
	
}
