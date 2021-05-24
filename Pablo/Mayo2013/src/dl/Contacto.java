package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Contacto database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Contacto.findAll", query="SELECT c FROM Contacto c"),
@NamedQuery(name="Contacto.findNombre", query = "SELECT c FROM Contacto c WHERE c.nombre=:nombre")
})
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idContacto;
	private String alias;
	private String apellidos;
	private String correo_electronico;
	private String nombre;
	private int numTelf;

	public Contacto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdContacto() {
		return this.idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}


	@Column(name="Alias")
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	@Column(name="Apellidos")
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	@Column(name="Correo_electronico")
	public String getCorreo_electronico() {
		return this.correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}


	@Column(name="Nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="NumTelf")
	public int getNumTelf() {
		return this.numTelf;
	}

	public void setNumTelf(int numTelf) {
		this.numTelf = numTelf;
	}

}