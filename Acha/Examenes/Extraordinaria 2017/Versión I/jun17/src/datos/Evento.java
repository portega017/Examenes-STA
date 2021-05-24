package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Evento database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e"),
@NamedQuery(name="Evento.findEstado",query="SELECT e FROM Evento e WHERE e.estado=:estado")

})
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private boolean estado;
	private String lugar;
	private String nombreEvento;

	public Evento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}


	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

}