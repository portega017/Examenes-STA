package datos;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the Grafico database table.
 * 
 */
@Entity
@NamedQuery(name="Grafico.findAll", query="SELECT g FROM Grafico g")

public class Grafico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGrafico;

	private int height;

	private String nombre;

	private int width;

	public Grafico() {
	}
	
	public int getIdGrafico() {
		return this.idGrafico;
	}

	public void setIdGrafico(int idGrafico) {
		this.idGrafico = idGrafico;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}