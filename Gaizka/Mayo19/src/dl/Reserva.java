package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Reservas database table.
 * 
 */
@Entity
@Table(name="Reservas")
@NamedQueries({
	@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r"),
	@NamedQuery(name="Reserva.findById", query="SELECT r FROM Reserva r WHERE r.idReservas = :id"),
	@NamedQuery(name="Reserva.findByAula", query="SELECT r FROM Reserva r WHERE r.aula = :aula")
})
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idReservas;
	private String aula;
	private Examen examene;

	public Reserva() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdReservas() {
		return this.idReservas;
	}

	public void setIdReservas(int idReservas) {
		this.idReservas = idReservas;
	}


	@Column(name="Aula")
	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}


	//uni-directional many-to-one association to Examen
	@ManyToOne
	@JoinColumn(name="Examenes_idExamen")
	public Examen getExamene() {
		return this.examene;
	}

	public void setExamene(Examen examene) {
		this.examene = examene;
	}

}