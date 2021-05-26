package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Entregas database table.
 * 
 */
@Entity
@Table(name="Entregas")
@NamedQueries({
	@NamedQuery(name="Entrega.findAll", query="SELECT e FROM Entrega e"),
	@NamedQuery(name="Entrega.findById", query="SELECT e FROM Entrega e WHERE e.idEntregas = :id"),
	@NamedQuery(name="Entrega.findByNombre", query="SELECT e FROM Entrega e WHERE e.nombre = :nombre")
})

public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEntregas;
	private String nombre;
	private String respuesta;
	private Examen examene;

	public Entrega() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdEntregas() {
		return this.idEntregas;
	}

	public void setIdEntregas(int idEntregas) {
		this.idEntregas = idEntregas;
	}


	@Column(name="Nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="Respuesta")
	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	//uni-directional many-to-one association to Examen
	@ManyToOne
	@JoinColumn(name="Examenes_idExamenes")
	public Examen getExamene() {
		return this.examene;
	}

	public void setExamene(Examen examene) {
		this.examene = examene;
	}

}