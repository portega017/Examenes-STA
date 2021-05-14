package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Equipo;
import datos.Juego;

@Stateless
@LocalBean
public class Servicios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Juego> getListaLuegos(){
		List<Juego> misJuegos=new ArrayList<>();
		try{
			misJuegos = em.createNamedQuery("Juego.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misJuegos;
	}
	
	public void addJuego(Juego j){
		em.persist(j);
	}
	
	public void borrarJuego(int id){
		Juego j = new Juego();
		j=em.find(Juego.class, id);
		em.remove(j);
	}
	
	@SuppressWarnings("unchecked")
	public List<Juego> getListaAnioSemana(int semana, int anio){
		List<Juego> misJuego = new  ArrayList<>();
		try{
			misJuego= em.createNamedQuery("Juego.findSEMANAyANIO").setParameter("anio", anio).setParameter("semana", semana).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misJuego;
	}
	
	public Equipo getMiequipo(int id){
		return em.find(Equipo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Equipo> getListaEQUIPOS(){
		List<Equipo> misEquipos=new ArrayList<>();
		try{
			misEquipos = em.createNamedQuery("Equipo.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misEquipos;
	}
}
