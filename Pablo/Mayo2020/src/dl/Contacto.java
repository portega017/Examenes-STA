package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the Contacto database table.
 * 
 */
@Entity
@NamedQueries({ 
		@NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
		@NamedQuery(name = "Contacto.findContagiado",query = "SELECT c FROM Contacto c WHERE (c.metros <= 2 AND c.minutos >= 5)")
		})
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date fecha;
	private float metros;
	private int minutos;
	private Persona persona1;
	private Persona persona2;

	public Contacto() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getMetros() {
		return this.metros;
	}

	public void setMetros(float metros) {
		this.metros = metros;
	}

	public int getMinutos() {
		return this.minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	// uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "Persona1_id", referencedColumnName = "id")
	public Persona getPersona1() {
		return this.persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	// uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name = "Persona2_id", referencedColumnName = "id")
	public Persona getPersona2() {
		return this.persona2;
	}

	public void setPersona2(Persona persona2) {
		this.persona2 = persona2;
	}

}