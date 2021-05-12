package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACTOS database table.
 * 
 */
@Entity
@Table(name="CONTACTOS")
@NamedQueries({
	@NamedQuery(name="Contacto.findAll", query="SELECT c FROM Contacto c"),
	@NamedQuery(name="Contacto.findById", query="SELECT c FROM Contacto c WHERE c.idCONTACTOS = :id"),
	@NamedQuery(name="Contacto.findByNombre", query="SELECT c FROM Contacto c WHERE c.nombre = :nombre")
})
public class Contacto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCONTACTOS;
	private String alias;
	private String apellidos;
	private String email;
	private String nombre;
	private String telefono;

	public Contacto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdCONTACTOS() {
		return this.idCONTACTOS;
	}

	public void setIdCONTACTOS(int idCONTACTOS) {
		this.idCONTACTOS = idCONTACTOS;
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


	@Column(name="Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="Nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="Telefono")
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}