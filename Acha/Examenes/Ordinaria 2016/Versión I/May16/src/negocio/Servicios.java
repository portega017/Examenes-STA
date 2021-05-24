package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Circle;
import datos.Rect;

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
	public List<Circle> getListaCirculos() {
		List<Circle> misCirculos = new ArrayList<Circle>();
		try {
			misCirculos = em.createNamedQuery("Circle.findAll").getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return misCirculos;
	}

	@SuppressWarnings("unchecked")
	public List<Rect> getListaRectangulos() {
		List<Rect> misRectangulos = new ArrayList<Rect>();

		try {
			misRectangulos = em.createNamedQuery("Rect.findAll").getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return misRectangulos;

	}

	public void addRectangulo(Rect rectangulo) {
		em.persist(rectangulo);
	}

	public void addCirculo(Circle circulo) {
		em.persist(circulo);
	}

	public void buscarCirculo(int id) {
		Circle c = new Circle();
		c = em.find(Circle.class, id);
		em.remove(c);
	}

	public void buscarRectangulo(int id) {
		Rect r = new Rect();
		r = em.find(Rect.class, id);
		em.remove(r);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


}
