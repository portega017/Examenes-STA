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
public class FormasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i = 0;
	@EJB
	private Servicios miEJB;
	private boolean mostrar=false;
	public List<Formas> getMisFormas() {
		List<Rect> misRectangulos = miEJB.getListaRectangulos();
		List<Circle> misCirculos = miEJB.getListaCirculos();
		List<Formas> misFormas = new ArrayList<Formas>();
		for (Rect r : misRectangulos) {
			Formas f = new Formas();
			String misDatos = new String();
			f.setTipo("Rectangulo");
			misDatos = "Height = " + r.getHeight() + " Width = " + r.getWidth() + " x = " + r.getX() + " y = "+ r.getY();
			f.setMisMedias(misDatos);
			misFormas.add(f);
		}
		
		for (Circle c : misCirculos) {
			Formas f = new Formas();
			String misDatos = new String();
			f.setTipo("Circulo");
			misDatos = "Cx = " + c.getCx() + " Cy = " + c.getCy() + " R = " + c.getR(); 
			f.setMisMedias(misDatos);
			misFormas.add(f);
		}

		return misFormas;
	}

	public int getI() {
		return i;
	}

	public void CirclesetI(int i) {
		this.i = i;
	}
	
	public String irACirculo(){
		i = 1;
		mostrar=true;
		return "circulo.xhtml";
	}

	public String irARectangulo(){
		i = 2;
		mostrar=true;
		return "rectangulo.xhtml";
	}
	
	public void deshacer(){
		if(i==1){
			List<Circle> miCirculo= miEJB.getListaCirculos();
			Circle aux=miCirculo.get(miCirculo.size()-1);
			miEJB.buscarCirculo(aux.getIdCircle());

		} if(i==2){
			List<Rect> miRectangulo= miEJB.getListaRectangulos();
			Rect aux=miRectangulo.get(miRectangulo.size()-1);
			miEJB.buscarRectangulo(aux.getIdRect());
		}
		mostrar=false;
		
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	public void generaFich(){
		Svg svg= new Svg();
		svg.setMisCIrculos(miEJB.getListaCirculos());
		svg.setMisRectangulos(miEJB.getListaRectangulos());
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Svg.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(svg, new File(System.getProperty("user.home")+"/mifich.svg"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
