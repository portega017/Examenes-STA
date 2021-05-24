package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Equipo database table.
 * 
 */
@Entity
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int division;

	private String nombre;

	//bi-directional many-to-one association to Juego
	@OneToMany(mappedBy="equipo1Bean")
	private List<Juego> juegos1;

	//bi-directional many-to-one association to Juego
	@OneToMany(mappedBy="equipo2Bean")
	private List<Juego> juegos2;

	public Equipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDivision() {
		return this.division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Juego> getJuegos1() {
		return this.juegos1;
	}

	public void setJuegos1(List<Juego> juegos1) {
		this.juegos1 = juegos1;
	}

	public Juego addJuegos1(Juego juegos1) {
		getJuegos1().add(juegos1);
		juegos1.setEquipo1Bean(this);

		return juegos1;
	}

	public Juego removeJuegos1(Juego juegos1) {
		getJuegos1().remove(juegos1);
		juegos1.setEquipo1Bean(null);

		return juegos1;
	}

	public List<Juego> getJuegos2() {
		return this.juegos2;
	}

	public void setJuegos2(List<Juego> juegos2) {
		this.juegos2 = juegos2;
	}

	public Juego addJuegos2(Juego juegos2) {
		getJuegos2().add(juegos2);
		juegos2.setEquipo2Bean(this);

		return juegos2;
	}

	public Juego removeJuegos2(Juego juegos2) {
		getJuegos2().remove(juegos2);
		juegos2.setEquipo2Bean(null);

		return juegos2;
	}

}