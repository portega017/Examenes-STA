package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the Circle database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Circle.findAll", query="SELECT c FROM Circle c"),
@NamedQuery(name="Circle.findGRAFICO", query="SELECT c FROM Circle c WHERE c.grafico.idGrafico=:idGrafico")
})
@XmlRootElement
public class Circle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCircle;

	private int cx;

	private int cy;

	private String fill;

	private int r;

	//uni-directional many-to-one association to Grafico
	@ManyToOne
	@JoinColumn(name="Grafico_idGrafico")
	private Grafico grafico;
	
	public Circle() {
	}
	
	@XmlTransient
	public int getIdCircle() {
		return this.idCircle;
	}

	public void setIdCircle(int idCircle) {
		this.idCircle = idCircle;
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
	public String getFill() {
		return this.fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}
	@XmlAttribute
	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	@XmlTransient
	public Grafico getGrafico() {
		return this.grafico;
	}

	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}

}