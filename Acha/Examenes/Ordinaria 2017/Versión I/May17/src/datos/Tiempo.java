package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tiempo database table.
 * 
 */
@Entity
@NamedQuery(name="Tiempo.findAll", query="SELECT t FROM Tiempo t")
public class Tiempo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTiempo;
	private long tiempo;
	private String usuario;
	private Categoria categoria;
	private Tarea tarea;

	public Tiempo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdTiempo() {
		return this.idTiempo;
	}

	public void setIdTiempo(int idTiempo) {
		this.idTiempo = idTiempo;
	}


	public long getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}


	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	//uni-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="Categoria_idCategoria")
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	//uni-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name="Tarea_idTarea")
	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}