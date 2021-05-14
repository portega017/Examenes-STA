package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the Rect database table.
 * 
 */
@Entity
@NamedQuery(name="Rect.findAll", query="SELECT r FROM Rect r")
public class Rect implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRect;
	private int height;
	private int width;
	private int x;
	private int y;

	public Rect() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlTransient
	public int getIdRect() {
		return this.idRect;
	}

	public void setIdRect(int idRect) {
		this.idRect = idRect;
	}

	@XmlAttribute
	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@XmlAttribute
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@XmlAttribute
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@XmlAttribute
	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

}