package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datos.Circle;
import datos.Rect;


@XmlRootElement
public class Svg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Rect> misRectangulos = new ArrayList<Rect>();

	private List<Circle> misCIrculos= new ArrayList<Circle>();

	
	@XmlElement(name="rect")
	public List<Rect> getMisRectangulos() {
		return misRectangulos;
	}
	public void setMisRectangulos(List<Rect> misRectangulos) {
		this.misRectangulos = misRectangulos;
	}
	
	@XmlElement(name="circle")
	public List<Circle> getMisCIrculos() {
		return misCIrculos;
	}
	public void setMisCIrculos(List<Circle> misCIrculos) {
		this.misCIrculos = misCIrculos;
	}
	

}
