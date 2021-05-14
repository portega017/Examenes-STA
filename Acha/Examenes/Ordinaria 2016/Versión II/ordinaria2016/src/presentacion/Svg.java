package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import datos.Circle;
import datos.Rect;
@XmlRootElement
public class Svg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Circle> circulos;
	private List<Rect> rectangulos;
	
	@XmlElement(name="circle")
	public List<Circle> getCirculos() {
		return circulos;
	}
	public void setCirculos(List<Circle> circulos) {
		this.circulos = circulos;
	}
	
	@XmlElement(name="rect")
	public List<Rect> getRectangulos() {
		return rectangulos;
	}
	public void setRectangulos(List<Rect> rectangulos) {
		this.rectangulos = rectangulos;
	}
	

}
