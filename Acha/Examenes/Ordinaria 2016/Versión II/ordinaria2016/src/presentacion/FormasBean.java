package presentacion;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import datos.Circle;
import datos.Rect;
import negocio.Servicios;
@Named
@SessionScoped
public class FormasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private Servicios miEJB;
	int deshacer=0;
	private boolean mostrarDeshacer=false;
	

	
	
	public List<Formas> getListaTodasMisFormas(){
		List<Formas> todasMisFormas = new ArrayList<Formas>();
		List<Circle> misCirculos=miEJB.getListaCirculos();
		List<Rect> misRectangulos=miEJB.getListaRectangulos();
		
		for(Circle c :misCirculos){
			Formas f=new Formas();
			f.setTipo("Circulo");
			f.setPropiedades("cx= "+c.getCx()+" cy= "+c.getCy()+" r="+c.getR());
			todasMisFormas.add(f);
		}
		
		for(Rect r :misRectangulos){
			Formas f=new Formas();
			f.setTipo("Rectangulo");
			f.setPropiedades("Height= "+r.getHeight()+" Width= "+r.getWidth()+" X="+r.getX()+" Y="+r.getY());
			todasMisFormas.add(f);
		}
		return todasMisFormas;
	}
	
	public String irANuevoCirculo(){
		deshacer=1;
		mostrarDeshacer=true;
		return "circulo.xhtml";
	}
	
	public String irANuevaRecta(){
		deshacer=2;
		mostrarDeshacer=true;
		return "rectangulo.xhtml";
	}
	public void deshacerAccion(){
		if(deshacer==1){
			List<Circle> misCirculos=miEJB.getListaCirculos();

			int id=misCirculos.get(misCirculos.size()-1).getIdcircle();
			miEJB.borrarCirculo(id);
			mostrarDeshacer=false;
		}else if(deshacer==2){
			List<Rect> misRectangulos=miEJB.getListaRectangulos();

			int id2=misRectangulos.get(misRectangulos.size()-1).getIdrect();
			miEJB.borrarRectangulo(id2);		mostrarDeshacer=false;
		}
	}

	public boolean isMostrarDeshacer() {
		return mostrarDeshacer;
	}

	public void setMostrarDeshacer(boolean mostrarDeshacer) {
		this.mostrarDeshacer = mostrarDeshacer;
	}
	
	public void generarFichero(){
		Svg svg= new Svg();
		svg.setCirculos(miEJB.getListaCirculos());
		svg.setRectangulos(miEJB.getListaRectangulos());
		File f = new File(System.getProperty("user.home")+"/fich.xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext=JAXBContext.newInstance(Svg.class);
			Marshaller m=jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(svg, f);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
