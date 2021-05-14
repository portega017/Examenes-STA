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

@LocalBean
@Stateless
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Juego> getListaJuego(){
		List<Juego> miList= new ArrayList<Juego>();
		try{
			miList=em.createNamedQuery("Juego.findAll").getResultList();
			
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miList;
	}
	public void borrarJUEGO(int id){
		Juego j=em.find(Juego.class, id);
		if(j!=null){
			em.remove(j);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Juego> getPartidos(int anio, int semana){
		List<Juego> miList= new ArrayList<Juego>();
		try{
			miList=em.createNamedQuery("Juego.findPartido").setParameter("anio", anio).setParameter("semana",semana).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miList;
	}
	
	public void addJuego(int id1,int id2,int anio,int semana,char resultado){
		Equipo e1=new Equipo();
		Equipo e2=new Equipo();
		Juego j=new Juego();
		e1=em.find(Equipo.class, id1);
		e2=em.find(Equipo.class, id2);
		if(e1!=null && e2!=null){
			j.setEquipo1Bean(e1);
			j.setEquipo2Bean(e2);
			j.setAnio(anio);
			j.setResultado(resultado);
			j.setSemana(semana);
			em.persist(j);

		}
		
	}
	
	
}
