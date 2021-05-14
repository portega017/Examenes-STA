package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Pedido;
import datos.Producto;
import negocio.Servicios;

@Named
@SessionScoped
public class CarritoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Pedido> misPedidos = new ArrayList<Pedido>();
	private Pedido pedido=new Pedido();
	private boolean comprar = false;
	private int indice=1;
	private boolean mostrarMensaje=false;
	private String[] mensaje={"NO HAY STOCK DISPONIBLE","COMPRA REALIZADA CORRECTAMENTE"};
	
	
	@EJB
	private Servicios miEJB;

	public List<Pedido> getMisPedidos() {
		return misPedidos;
	}

	public void setMisPedidos(List<Pedido> misPedidos) {
		this.misPedidos = misPedidos;
	} 
	
	public float getPrecioTotal(){
		float precio=0;
		for(int i=0;i<misPedidos.size();i++){
			float aux = misPedidos.get(i).getCantidad()*misPedidos.get(i).getProductoBean().getPrecio();
			precio = precio + aux;
		}
		return precio;
	}
	
	
	public int addProducto(int id){
		int j=0;
		Producto producto = miEJB.buscarProducto(id);
		if(producto.getStock()<=0){
			j=1;
		}else {
			for(int i=0;i<misPedidos.size();i++){
				if(misPedidos.get(i).getProductoBean().getId()==id){
					j=2;
					int cantidad = misPedidos.get(i).getCantidad();
					misPedidos.get(i).setCantidad(cantidad+1);
					
				}
			} 
			if(j!=2){
				Pedido p = new Pedido();
				p.setProductoBean(producto);
				p.setCantidad(1);
				misPedidos.add(p);
				j=3;
			}
			
		}
		mostrarMensaje=false;
		
		return j;
		
		
	}

	
	public void addPedido(){
		int k=0;

		for(Pedido p : misPedidos){
			Producto aux = new Producto();
			aux=miEJB.buscarProducto(p.getProductoBean().getId());
			if(p.getCantidad()>aux.getStock()){
				k=1;
				indice=0;
			}
		}
		if(k==1){
			misPedidos = new ArrayList<Pedido>();
		}else{
			indice=1;
			for(Pedido p : misPedidos){
				p.setDireccion(pedido.getDireccion());
				p.setNombre(pedido.getNombre());
				p.setDni(pedido.getDni());
				miEJB.cambiarstock(p);
				miEJB.addPedido(p);
			}
			misPedidos = new ArrayList<Pedido>();
		}
		comprar=false;
		mostrarMensaje=true;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isComprar() {
		return comprar;
	}

	public void setComprar(boolean comprar) {
		this.comprar = comprar;
	}
	
	public void pedirDatos(){
		comprar=true;
		mostrarMensaje=false;
	}

	public String[] getMensaje() {
		return mensaje;
	}

	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public String mostrarINFORMACION(){
		return mensaje[indice];
	}

	public boolean isMostrarMensaje() {
		return mostrarMensaje;
	}

	public void setMostrarMensaje(boolean mostrarMensaje) {
		this.mostrarMensaje = mostrarMensaje;
	}
}
