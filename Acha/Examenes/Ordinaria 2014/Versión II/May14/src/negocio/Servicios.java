package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
		List<Alumno> misAlumnos=new ArrayList<>();
		
		try{
			misAlumnos= em.createNamedQuery("Alumno.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misAlumnos;
	}

	
	@SuppressWarnings("unchecked")
	public List<Asignatura> getListaAsignaturas(){
		List<Asignatura> misAsignaturas=new ArrayList<>();
		
		try{
			misAsignaturas= em.createNamedQuery("Asignatura.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misAsignaturas;
	}
	
	
	public void addAsignatura(Asignatura a){
		em.persist(a);
	}
	
	public void addAlumno(Alumno a){
		em.persist(a);
	}
	
	public void borrarAlumno(String dni){
		Alumno a = new Alumno();
		a=em.find(Alumno.class, dni);
		if(a!=null){
			em.remove(a);
		}
	}
	
	public void borrarAsignatura(int id){
		Asignatura a = new Asignatura();
		a=em.find(Asignatura.class, id);
		if(a!=null){
			em.remove(a);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> getListaNotasALUMNO(String dni){
		List<Nota> misNotas = new ArrayList<>();
		try{
		misNotas=em.createNamedQuery("Notas.findNotasAlumno").setParameter("dni", dni).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return misNotas;
	}
	
	public Alumno getMiAlumno(String dni){
		return em.find(Alumno.class, dni);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> getListaNotas(){
		List<Nota> misNotas = new ArrayList<>();
		try{
			misNotas= em.createNamedQuery("Nota.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		
		return misNotas;
	}
	
	
	public void addNota(Nota n){
		em.persist(n);
	}
	
	public void borrarNota(int idNota){
		Nota n= new Nota();
		em.remove(n);
	}
	
	public Asignatura getMiAsignatura(int id){
		return em.find(Asignatura.class, id);
	}
	
}
