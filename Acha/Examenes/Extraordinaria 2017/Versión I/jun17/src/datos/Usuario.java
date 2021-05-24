package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Usuario database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
@NamedQuery(name="Usuario.findUsuario",query="SELECT u FROM Usuario u WHERE u.nombreUsuario=:usuario")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private String contraseña;
	private String nombreUsuario;

	public Usuario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}