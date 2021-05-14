package datos;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the Voto database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Voto.findAll", query="SELECT v FROM Voto v"),
@NamedQuery(name="Voto.findUSUARIOEVENTO", query="SELECT v FROM Voto v WHERE v.usuario=:usuario AND v.evento=:evento"),
@NamedQuery(name="Voto.findEVENTO", query="SELECT v FROM Voto v WHERE v.evento=:evento")
})
@XmlRootElement
public class Voto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idVoto;
	private String dia;
	private Boolean disponiblidad;
	private Evento evento;
	private Usuario usuario;

	public Voto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement
	public int getIdVoto() {
		return this.idVoto;
	}

	public void setIdVoto(int idVoto) {
		this.idVoto = idVoto;
	}

	@XmlElement
	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	@XmlElement
	public Boolean getDisponiblidad() {
		return this.disponiblidad;
	}

	public void setDisponiblidad(Boolean disponiblidad) {
		this.disponiblidad = disponiblidad;
	}


	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="Evento_idEvento")
	@XmlElement
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_dni")
	@XmlElement
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}