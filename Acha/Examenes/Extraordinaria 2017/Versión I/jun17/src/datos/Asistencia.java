package datos;

import java.io.Serializable;


import javax.persistence.*;


/**
 * The persistent class for the Asistencia database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Asistencia.findAll", query="SELECT a FROM Asistencia a"),
@NamedQuery(name="Asistencia.findEVENTOPERSONA",query="SELECT a FROM Asistencia a WHERE a.usuario.nombreUsuario=:usuario AND a.evento.nombreEvento=:evento"),
@NamedQuery(name="Asistencia.findEVENTO",query="SELECT a FROM Asistencia a WHERE a.evento.nombreEvento=:evento")

})
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAsistencia;
	private String dia;
	private boolean disponibilidad;
	private Evento evento;
	private Usuario usuario;


	public Asistencia() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdAsistencia() {
		return this.idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}


	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}


	public boolean getDisponibilidad() {
		return this.disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}


	//uni-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="Evento_idEvento")
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_idUsuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void addAsistencia(){
		
	}




}