package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the rect database table.
 * 
 */
@Entity
@Table(name="rect")
@NamedQuery(name="Rect.findAll", query="SELECT r FROM Rect r")
@XmlRootElement
public class Rect implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idrect;
	private int height;
	private int width;
	private int x;
	private int y;

	public Rect() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlTransient
	public int getIdrect() {
		return this.idrect;
	}

	public void setIdrect(int idrect) {
		this.idrect = idrect;
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