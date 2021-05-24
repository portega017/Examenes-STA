package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datos.Circle;
import datos.Grafico;
import datos.Text;
import presentacion.GraficoDatos;

@LocalBean
@Stateless
@Path("servicios/")
public class Servicios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	public List<Grafico> getListaGraficos(){
		List<Grafico> miLista = new ArrayList<Grafico>();
		try{
			miLista = em.createNamedQuery("Grafico.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Circle> getListaCirculosGRAFICO(int idgrafico){
		List<Circle> miLista = new ArrayList<Circle>();
		try{
			miLista=em.createNamedQuery("Circle.findGRAFICO").setParameter("idGrafico", idgrafico).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Text> getListaTextoGRAFICO(int idgrafico){
		List<Text> miLista = new ArrayList<Text>();
		try{
			miLista=em.createNamedQuery("Text.findGRAFICO").setParameter("idGrafico", idgrafico).getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public void borrarGrafico(int id){
		Grafico g = em.find(Grafico.class, id);
		em.remove(g);
	}
	
	public void addGrafico(Grafico g){
		em.persist(g);
	}
	public void addNuevoCirculo(Circle c ,int idgraf){
		Grafico g=em.find(Grafico.class, idgraf);
		c.setGrafico(g);
		em.persist(c);
	}
	
	public void addNuevoTexto(Text c ,int idgraf){
		Grafico g=em.find(Grafico.class, idgraf);
		c.setGrafico(g);
		em.persist(c);
		
	}
	
	public Grafico getMigrafico(int id){
		return em.find(Grafico.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	@Path("/lista")
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public ListaGrafico getListaGraficosREST(){
		List<Grafico> miLista = new ArrayList<Grafico>();
		ListaGrafico miFinal= new ListaGrafico();
		try{
			miLista = em.createNamedQuery("Grafico.findAll").getResultList();
			List<GraficoDatos> miLista1= new ArrayList<GraficoDatos>();
			List<Circle> misCirculos=new ArrayList<>();
			List<Text> misTextos=new ArrayList<>();

			for(int i=0;i<miLista.size();i++){
				misCirculos=getListaCirculosGRAFICO(miLista.get(i).getIdGrafico());
				misTextos=getListaTextoGRAFICO(miLista.get(i).getIdGrafico());
				GraficoDatos g=new GraficoDatos();
				 g.setGrafico(miLista.get(i));
				 g.setCirculo(misCirculos.size());
				 g.setTexto(misTextos.size());
				 miLista1.add(g);
				 
			}
			miFinal.setMiLista(miLista1);
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miFinal;
	}
}
