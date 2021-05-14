package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Pedido;
import negocio.Servicios;
@Named
@SessionScoped
public class CarritoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pedido> miLista = new ArrayList<Pedido>();
	@EJB
	private Servicios miEJB;
	private boolean mostrar=false;

	
	
	public boolean comprar(int idproducto){
	boolean ok =false;
		
		
		for(Pedido p:miLista){
			if(ok==false){
			ok=miEJB.addPedido(idproducto, p);
			
			}
		} if(ok==false){
			Pedido p=miEJB.addProducto(idproducto);
			if(p!=null){
			miLista.add(p);
			}
		}
		return ok;
	}
	
	public List<Pedido> getListaPedido(){
		return miLista;
	}
	public double getPrecioTotal(){
		return miEJB.obtenerPrecio(miLista);
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	public void realiarCompraPedidos(String dni,String dir,String nom){
		miEJB.persistirPedidos(miLista, dni,dir,nom);
		mostrar=false;
		miLista = new ArrayList<Pedido>();
	}
	



}
