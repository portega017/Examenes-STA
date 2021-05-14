package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Juego database table.
 * 
 */
@Entity
@NamedQuery(name="Juego.findAll", query="SELECT j FROM Juego j")
public class Juego implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int anio;

	private String resultado;

	private int semana;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo1")
	private Equipo equipo1Bean;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo2")
	private Equipo equipo2Bean;

	public Juego() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getResultado() {
		return this.resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getSemana() {
		return this.semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}

	public Equipo getEquipo1Bean() {
		return this.equipo1Bean;
	}

	public void setEquipo1Bean(Equipo equipo1Bean) {
		this.equipo1Bean = equipo1Bean;
	}

	public Equipo getEquipo2Bean() {
		return this.equipo2Bean;
	}

	public void setEquipo2Bean(Equipo equipo2Bean) {
		this.equipo2Bean = equipo2Bean;
	}

}