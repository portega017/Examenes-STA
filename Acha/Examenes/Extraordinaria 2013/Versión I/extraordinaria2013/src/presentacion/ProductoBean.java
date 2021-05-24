package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Producto;
import negocio.Servicios;

@RequestScoped
@Named
public class ProductoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Producto producto = new Producto();
	private boolean mostrar= false;
	@EJB
	private Servicios miEJB;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public List<Producto> getListaProducto(){
		return miEJB.getListaProducto();
	}
	
	public String comprobarStock(int id){
		producto = miEJB.getProducto(id);
		String stock;
		if(producto.getStock()>0){
			stock=String.valueOf(producto.getStock());	
		}else{
			stock="NO HAY STOCK";
		}
		return stock;
	}
	public boolean mostrar(int stock){
		if(stock>0){
			mostrar=true;
		}else{
			mostrar=false;
		}
		return mostrar;
	}
}
