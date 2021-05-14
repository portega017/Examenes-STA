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
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	private boolean notaCorrecta=false;

	@SuppressWarnings("unchecked")
	public List<Alumno> getAlumnos() {
		return em.createNamedQuery("Alumno.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Asignatura> getAsignaturas() {
		return em.createNamedQuery("Asignatura.findAll").getResultList();
	}

	public void deleteAlumno(String dni) {
		Alumno a = em.find(Alumno.class, dni);
		em.remove(a);

	}

	public void deleteAsignatura(int id) {
		Asignatura a = em.find(Asignatura.class, id);
		em.remove(a);

	}

	public void addAlumno(Alumno a) {
		em.persist(a);

	}

	public void addAsignatura(Asignatura a) {
		em.persist(a);
	}
	
	public boolean comprobarAlumno(String dni) {
		boolean ok = false;
		Alumno a = em.find(Alumno.class, dni);
		if (a != null) {
			ok = true;
		}
		System.out.println("HOLS");
		return ok;

	}

	@SuppressWarnings("unchecked")
	public List<Nota> obtenerNotas(String dni) {
		
		
		 List<Nota>miLista = new ArrayList<Nota>();
		
			if (comprobarAlumno(dni) == true) {
				try {
					miLista = em.createNamedQuery("Nota.findAlumno").setParameter("dni", dni).getResultList();
				} catch (NoResultException e) {
					
				}
			}
		
		return miLista;
	}
	public boolean addNota(String dni ,String nombreAsig,int nota){
		Alumno a=em.find(Alumno.class, dni);
		//Asignatura asig=em.find(Asignatura.class, idAsignatuara);
		Asignatura asig =new Asignatura();
		try{
			asig=(Asignatura) em.createNamedQuery("Asignatura.findNombre").setParameter("nombre", nombreAsig).getSingleResult();
			if(a!=null && asig!=null){
				Nota n=new Nota();
				n.setNota(nota);
				n.setAlumnoBean(a);
				n.setAsignaturaBean(asig);
				em.persist(n);
				notaCorrecta=false;
			}
		}catch (NoResultException e) {
			notaCorrecta=true;
			// TODO: handle exception
		}
			
		
		return notaCorrecta;
	}

	@SuppressWarnings("unchecked")
	public List<Nota> obtenerNotasTotaltes(){
		List<Nota> miLista=new ArrayList<Nota>();
		try{
		 miLista=em.createNamedQuery("Nota.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
		
	}
	public void borrarNota(int idNota){
		Nota n = em.find(Nota.class,idNota);
		em.remove(n);
	}
}
