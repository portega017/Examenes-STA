package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Categoria;
import datos.Tarea;
import datos.Tiempo;

@Stateless
@LocalBean
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public void addCategoria(String nombre) {
		Categoria cat = new Categoria();
		cat.setNombreCategoria(nombre);
		em.persist(cat);
	}

	public void addTarea(String nombreTarea) {

		Tarea tarea = new Tarea();
		tarea.setNombreTarea(nombreTarea);
		em.persist(tarea);

	}

	public void borrarCategoria(int id) {
		Categoria cat = new Categoria();
		cat = em.find(Categoria.class, id);
		if (cat != null) {
			em.remove(cat);
		}
	}

	public void borrarTarea(int id) {
		Tarea tarea = new Tarea();
		tarea = em.find(Tarea.class, id);
		if (tarea != null) {
			em.remove(tarea);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Tarea> getListaTareas() {
		List<Tarea> miLista = new ArrayList<Tarea>();
		try {
			miLista = em.createNamedQuery("Tarea.findAll").getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> getListaCategoria() {
		List<Categoria> miLista = new ArrayList<Categoria>();
		try {
			miLista = em.createNamedQuery("Categoria.findAll").getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public long getSegundos(){
		return System.currentTimeMillis();
		
	}
	
	public Tarea getTarea(int id){
		return em.find(Tarea.class, id);
	}
	
	public Categoria getCategoria(int id){
		return em.find(Categoria.class, id);
	}
	
	public void addTiempo(Tiempo t){
		em.persist(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tiempo> getTiempos(){
		List<Tiempo> miLista = new ArrayList<Tiempo>();
		try {
			miLista = em.createNamedQuery("Tiempo.findAll").getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}

}
