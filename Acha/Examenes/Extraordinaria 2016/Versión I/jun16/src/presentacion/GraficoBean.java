package presentacion;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import datos.Circle;
import datos.Grafico;
import datos.Text;
import negocio.ListaGrafico;
import negocio.Servicios;


@Named
@RequestScoped
public class GraficoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraficoDatos grafico=new GraficoDatos();
	@EJB
	private Servicios miEJB;
	
	public GraficoDatos getGrafico() {
		return grafico;
	}
	public void setGrafico(GraficoDatos grafico) {
		this.grafico = grafico;
	}
	
	public List<GraficoDatos> getMisGraficos(){
		List<GraficoDatos> miLista= new ArrayList<GraficoDatos>();
		List<Circle> misCirculos=new ArrayList<>();
		List<Text> misTextos=new ArrayList<>();
		List<Grafico>aux= miEJB.getListaGraficos();
		for(int i=0;i<aux.size();i++){
			misCirculos=miEJB.getListaCirculosGRAFICO(aux.get(i).getIdGrafico());
			misTextos=miEJB.getListaTextoGRAFICO(aux.get(i).getIdGrafico());
			GraficoDatos g=new GraficoDatos();
			 g.setGrafico(aux.get(i));
			 g.setCirculo(misCirculos.size());
			 g.setTexto(misTextos.size());
			 miLista.add(g);
			 
		}
		return miLista;
		
	}
	
	public void borrarGrafico(int id){
		miEJB.borrarGrafico(id);
	}
	
	public void addGrafico(){
		miEJB.addGrafico(grafico.getGrafico());
	}
	
	public void addNuevoGrafico(int idgrafico){
		List<Circle> misCirculos=miEJB.getListaCirculosGRAFICO(idgrafico);
		List<Text>misTextos=miEJB.getListaTextoGRAFICO(idgrafico);
		Grafico g= new Grafico();
		g=miEJB.getMigrafico(idgrafico);
		Svg s= new Svg();
		s.setCircle(misCirculos);
		s.setText(misTextos);
		s.setHeigth(g.getHeight());
		s.setWidth(g.getWidth());
		JAXBContext jaxbContext;
		
		try {
			jaxbContext=JAXBContext.newInstance(Svg.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(s, new File(System.getProperty("user.home")+"/"+g.getNombre()+".svg"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<GraficoDatos> getListaREST(){

		ListaGrafico miListaux=ClientBuilder.newClient().target("http://localhost:8080/jun16/rest/servicios/lista").request(MediaType.APPLICATION_XML).get(ListaGrafico.class);
		return miListaux.getMiLista();
	}
	


}
