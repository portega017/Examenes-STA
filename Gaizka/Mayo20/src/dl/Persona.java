package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Persona database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p"),
	@NamedQuery(name="Persona.findById", query="SELECT p FROM Persona p WHERE p.id = :id"),
	@NamedQuery(name="Persona.findByUpTemp", query="SELECT p FROM Persona p WHERE p.temperatura >= :temp")
})
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean avisado;
	private float temperatura;
	private Municipio municipio;

	public Persona() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean getAvisado() {
		return this.avisado;
	}

	public void setAvisado(boolean avisado) {
		this.avisado = avisado;
	}


	public float getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}


	//uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_id")
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}