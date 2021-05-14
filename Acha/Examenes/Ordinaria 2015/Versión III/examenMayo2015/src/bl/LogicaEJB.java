package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Equipo;
import dl.Juego;

/**
 * Session Bean implementation class LogicaEJB
 */
@Stateless
@LocalBean
public class LogicaEJB {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public LogicaEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Juego> getListaJuego()
    {
    	@SuppressWarnings("unchecked")
    	List<Juego> lista = em.createNamedQuery("Juego.findAll").getResultList();
    	
    	return lista;
    }
    public List<Juego> getListaJuegoConcreta(int anio, int semana)
    {
    	@SuppressWarnings("unchecked")
    	List<Juego> lista = (List<Juego>)em.createQuery("SELECT j FROM Juego j WHERE j.anio LIKE :anio AND j.semana LIKE :semana").setParameter("anio", anio).setParameter("semana", semana).getResultList();
    	return lista;
    }
    
    public void addJuego(int anio, int semana, String resultado, String nombreEquipo1, String nombreEquipo2)
    {
    	Equipo equipo1 = getEquipoNombre(nombreEquipo1);
    	Equipo equipo2 = getEquipoNombre(nombreEquipo2);
    	
    	if(equipo1 !=null && equipo2 != null)
    	{
    		Juego juego = new Juego();
    		juego.setAnio(anio);
    		juego.setEquipo1Bean(equipo1);
    		juego.setEquipo2Bean(equipo2);
    		juego.setSemana(semana);
    		juego.setResultado(resultado);
    		
    		em.persist(juego);
    	}
    	
    }
    
    private Equipo getEquipoNombre(String nombre)
    {
    	Equipo equipo = (Equipo) em.createQuery("SELECT e FROM Equipo e WHERE e.nombre LIKE :nombre").setParameter("nombre", nombre).getSingleResult();
    	return equipo;
    }
    public void eliminarJuego(int id)
    {
    	Juego juego = em.find(Juego.class, id);
    	
    	if(juego!=null)
    	{
    		em.remove(juego);
    	}
    }

}
