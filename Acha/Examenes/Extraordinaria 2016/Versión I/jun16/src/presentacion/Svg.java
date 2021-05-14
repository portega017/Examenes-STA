package presentacion;



import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import datos.Circle;
import datos.Text;

@XmlRootElement
public class Svg {
	private int width;
	private int heigth;
	private List<Circle> circle = new ArrayList<Circle>();
	private List<Text> text = new ArrayList<Text>();

	@XmlAttribute
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	@XmlAttribute
	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	@XmlElement
	public List<Circle> getCircle() {
		return circle;
	}

	public void setCircle(List<Circle> circle) {
		this.circle = circle;
	}

	@XmlElement
	public List<Text> getText() {
		return text;
	}

	public void setText(List<Text> text) {
		this.text = text;
	}
	
	

}
