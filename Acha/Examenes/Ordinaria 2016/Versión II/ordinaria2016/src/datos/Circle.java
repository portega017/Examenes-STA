package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the circle database table.
 * 
 */
@Entity
@Table(name="circle")
@NamedQuery(name="Circle.findAll", query="SELECT c FROM Circle c")
@XmlRootElement
public class Circle implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idcircle;
	private int cx;
	private int cy;
	private int r;

	public Circle() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlTransient
	public int getIdcircle() {
		return this.idcircle;
	}
	
	public void setIdcircle(int idcircle) {
		this.idcircle = idcircle;
	}

	@XmlAttribute
	public int getCx() {
		return this.cx;
	}

	public void setCx(int cx) {
		this.cx = cx;
	}

	@XmlAttribute
	public int getCy() {
		return this.cy;
	}

	public void setCy(int cy) {
		this.cy = cy;
	}

	@XmlAttribute
	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}

}