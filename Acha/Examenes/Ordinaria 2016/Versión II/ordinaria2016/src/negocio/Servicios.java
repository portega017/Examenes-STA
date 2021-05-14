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

@LocalBean
@Stateless
public class Servicios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Circle> getListaCirculos(){
		List<Circle> misCirculos = new ArrayList<>();
		try{
			misCirculos=em.createNamedQuery("Circle.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misCirculos;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Rect> getListaRectangulos(){
		List<Rect> misRectangulos = new ArrayList<>();
		try{
			misRectangulos=em.createNamedQuery("Rect.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misRectangulos;
		
	}
	
	public void addCircle(Circle c){
		em.persist(c);
	}
	
	public void addRectangulo(Rect r){
		em.persist(r);
	}
	
	public void borrarCirculo(int idCirculo){
		Circle c = em.find(Circle.class, idCirculo);
		em.remove(c);
	}
	public void borrarRectangulo(int idRect){
		Rect r = em.find(Rect.class, idRect);
		em.remove(r);
	}
}
