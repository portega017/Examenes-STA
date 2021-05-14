package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;


/**
 * The persistent class for the Text database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Text.findAll", query="SELECT t FROM Text t"),
@NamedQuery(name="Text.findGRAFICO", query="SELECT t FROM Text t WHERE t.grafico.idGrafico=:idGrafico")
})
@XmlRootElement
public class Text implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int idTexto;

	private String fill;

	private String letra;

	private int x;

	private String y;

	//uni-directional many-to-one association to Grafico
	@ManyToOne
	@JoinColumn(name="Grafico_idGrafico")
	private Grafico grafico;

	public Text() {
	}
	
	@XmlTransient
	public int getIdTexto() {
		return this.idTexto;
	}

	public void setIdTexto(int idTexto) {
		this.idTexto = idTexto;
	}
	@XmlAttribute
	public String getFill() {
		return this.fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}
	
	@XmlValue
	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	@XmlAttribute
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	@XmlAttribute
	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	@XmlTransient
	public Grafico getGrafico() {
		return this.grafico;
	}

	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}

}