package bl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Entrega;
import dl.Examen;
import dl.Reserva;

@Stateless
@LocalBean
public class LogicaNegocio implements Serializable {

	@PersistenceContext
	private EntityManager em;
	private static final long serialVersionUID = 1L;

	public String altaExamen(Examen ex, List<String> listaAulas) {
		String s = new String();
		if(em.createNamedQuery("Examen.findByAsignatura").setParameter("asignatura", ex.getAsignatura()).getResultList().isEmpty()) {
			for(String a : listaAulas) {
					if(em.createNamedQuery("Reserva.findByAula").setParameter("aula", a).getResultList().isEmpty()) {
						em.persist(ex);
						Reserva r = new Reserva();
						r.setAula(a);
						r.setExamene(ex);
						em.persist(r);
						s = "Examen añadido satisfactoriamente";
					}else {
						s = "Alguna de las aulas que ha elegido está ocupada";
					}
			}
		}else {
			s = "El examen que intentas añadir ya existe";	
		}
		return s;
	}
	
	public String altaResp(Entrega e, int idxExam) {
		String s = new String();
		Examen ex = em.createNamedQuery("Examen.findById",Examen.class).setParameter("id", idxExam).getSingleResult();
		
		e.setExamene(ex);
		
		System.err.println("Nombre Entrega: " + e.getNombre() + "Respuesta: " + e.getRespuesta() + "\nAsignatura: " + ex.getAsignatura());
		
		em.persist(e);
		
		
		return "Su respuesta ha sido dada de alta satisfactoriamente";
	}
	
	public List<Examen> getExamenes(){
		return em.createNamedQuery("Examen.findAll", Examen.class).getResultList();
	}
	
	public HashSet<Examen> getExamenesAlumno(String nombre){
		List<Entrega> lent = em.createNamedQuery("Entrega.findByNombre",Entrega.class).setParameter("nombre", nombre).getResultList();
		List<Examen> lexam = em.createNamedQuery("Examen.findAll",Examen.class).getResultList();
		HashSet<Examen> lexamSinHacer = new HashSet<Examen>();
		if(!lent.isEmpty()) {
			for(Entrega ent : lent) {
				for(Examen ex : lexam) {
					if(ent.getExamene().getIdExamen() !=  ex.getIdExamen()) {
						lexamSinHacer.add(ex);
					}
				}
			}
		}else {
			for(Examen e : lexam)
				lexamSinHacer.add(e);
		}
		return lexamSinHacer;
	}
}
