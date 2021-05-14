package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.resteasy.util.FindAnnotation;

import datos.Alumno;
import datos.Asignatura;
import datos.Nota;


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
	public List<Alumno> getListaAlumno(){
		List<Alumno> miLista= new ArrayList<Alumno>();
		try{
			miLista=em.createNamedQuery("Alumno.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		
		return miLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Asignatura> getListaAsignatura(){
		List<Asignatura> miLista= new ArrayList<Asignatura>();
		try{
			miLista=em.createNamedQuery("Asignatura.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		
		return miLista;
	}
	
	public void borrarAlumno(String dni){
		Alumno al=em.find(Alumno.class, dni);
		if(al!=null){
		em.remove(al);
		}
	}
	public void borrarAsignatura(int id){
		Asignatura al=em.find(Asignatura.class, id);
		if(al!=null){
		em.remove(al);
		}
	}

	public void addAlumno(Alumno a){
		em.persist(a);
	}
	
	public void addAsignatura(Asignatura a){
		em.persist(a);
	}
	
	public void addNota(String dni, int id,String nota){
		Nota n=new Nota();
		try{
			n=(Nota) em.createNamedQuery("Nota.addNota").setParameter("dni", dni).setParameter("id", id).getSingleResult();			
			n.setNota(nota);
			em.persist(n);
				
			
		}catch (NoResultException e) {
			
			Alumno a = em.find(Alumno.class, dni);
			Asignatura asig=em.find(Asignatura.class, id);
			
			if(a!=null && asig!=null){
				n.setAlumno(a);
				n.setAsignatura(asig);
				n.setNota(nota);
				em.persist(n);
			}
			
		}
		
		
		
	}
	
	
	public boolean dniCorrecto(String dni){
		boolean correcto=false;
		Alumno a = em.find(Alumno.class, dni);
		if(a !=null){
			correcto =true;
		}
		return correcto;
	}
	
	public boolean idAsigCorrecto(int id){
		boolean correcto=false;
		Asignatura a = em.find(Asignatura.class, id);
		if(a !=null){
			correcto =true;
		}
		return correcto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> getListaNotas(){
		List<Nota> miLista =new ArrayList<Nota>();
		try{
			miLista=em.createNamedQuery("Nota.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public void borrarNota(int id){
		Nota n=em.find(Nota.class, id);
		if (n!=null){
			em.remove(n);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> getNOTASALUMNO(String dni){
		List<Nota> a = new ArrayList<Nota>();
			try{	
		a=em.createNamedQuery("Nota.findALUMNO").setParameter("dni", dni).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
			return a;
			
	}

	

}
