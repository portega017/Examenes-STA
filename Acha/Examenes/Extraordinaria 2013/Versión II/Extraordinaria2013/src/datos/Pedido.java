package datos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Pedido database table.
 * 
 */
@Entity
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int cantidad;
	private String direccion;
	private String dni;
	private String nombre;
	private Producto productoBean;

	public Pedido() {
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="producto")
	public Producto getProductoBean() {
		return this.productoBean;
	}

	public void setProductoBean(Producto productoBean) {
		this.productoBean = productoBean;
	}
	

}