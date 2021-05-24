package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Contacto database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Contacto.findAll", query="SELECT c FROM Contacto c"),
@NamedQuery(name="Contacto.finPorNombre",query="SELECT c FROM Contacto c WHERE c.nombre=:nombre")
})

public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idContacto;
	private String alias;
	private String apellidos;
	private String correo;
	private String nombre;
	private String telefono;

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


	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}