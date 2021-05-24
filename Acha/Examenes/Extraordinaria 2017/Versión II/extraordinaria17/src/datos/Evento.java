package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the Evento database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e"),
@NamedQuery(name="Evento.findEstado", query="SELECT e FROM Evento e WHERE e.estado=:estado")
})
@XmlRootElement
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private Boolean estado;
	private String lugar;
	private String nombreEvento;


	public Evento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@XmlElement
	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	@XmlElement
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@XmlElement
	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@XmlElement
	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}




}