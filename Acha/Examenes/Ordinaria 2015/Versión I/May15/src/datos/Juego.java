package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Juego database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Juego.findAll", query="SELECT j FROM Juego j"),
@NamedQuery(name="Juego.findPartido", query="SELECT j FROM Juego j WHERE j.semana=:semana AND j.anio=:anio")
})
public class Juego implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int anio;
	private char resultado;
	private int semana;
	private Equipo equipo1Bean;
	private Equipo equipo2Bean;

	public Juego() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	public char getResultado() {
		return this.resultado;
	}

	public void setResultado(char resultado) {
		this.resultado = resultado;
	}


	public int getSemana() {
		return this.semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}


	//uni-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo1")
	public Equipo getEquipo1Bean() {
		return this.equipo1Bean;
	}

	public void setEquipo1Bean(Equipo equipo1Bean) {
		this.equipo1Bean = equipo1Bean;
	}


	//uni-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo2")
	public Equipo getEquipo2Bean() {
		return this.equipo2Bean;
	}

	public void setEquipo2Bean(Equipo equipo2Bean) {
		this.equipo2Bean = equipo2Bean;
	}

}