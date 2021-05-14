package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import datos.Pedido;
import datos.Producto;

@LocalBean
@Stateless
public class Servicios {
	
	@PersistenceContext
	private EntityManager em;
	private boolean mostrar=false;
	private boolean stock=false;
	
	@SuppressWarnings("unchecked")
	public List<Producto> getListaProducto(){
		List <Producto> miLista=new ArrayList<Producto>();
		try{
			miLista=em.createNamedQuery("Producto.findAll").getResultList();
		}catch (NoResultException e) {
			// TODO: handle exception
		}
		return miLista;
	}
	
	public Pedido addProducto(int idProducto)
	{
		Pedido ped =null;
		Producto p=em.find(Producto.class, idProducto);
		if(p.getStock()>0){
		ped=new Pedido();
		ped.setCantidad(1);
		ped.setProductoBean(p);
		int stock=p.getStock();
		p.setStock(stock-1);
		em.persist(p);
		}
		return ped;
	}
	
	public boolean comprobarStock(int idProducto){
		Producto p=em.find(Producto.class, idProducto);
		if(p.getStock()==0 && stock==true){
			mostrar = true;
		}
		return mostrar;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> getListaPedidos(){
		List<Pedido> lista=new ArrayList<Pedido>();
		try{
			lista=em.createNamedQuery("Pedido.findAll").getResultList();
		}catch(NoResultException e){
			
		}
		return lista;
	}
	
	public void persistirPedidos(List<Pedido> pedido,String dni,String dir,String nom){
		for(Pedido p: pedido){
			
			p.setDni(dni);
			p.setNombre(nom);
			p.setDireccion(dir);
			em.persist(p);
		}
	}
	
	
	public boolean addPedido(int idproducto,Pedido pedido){
		Producto p = new Producto();
		boolean ok =false;		
		p=em.find(Producto.class,idproducto);
		if (p.getStock()>0){
			if( pedido !=null){
				 int idProducto=pedido.getProductoBean().getId();
				 if(idProducto ==p.getId()){
					 int cantidad=pedido.getCantidad();
					 cantidad = cantidad + 1;
					 pedido.setCantidad(cantidad);
					 int stock = p.getStock();
					 p.setStock(stock -1);
					 em.persist(p);
					 ok=true; 
				 }
			}
		}else{
			ok=true;
		}
		
		return ok;
	}
	public double obtenerPrecio(List<Pedido> miLista){
		double precio=0;
		for(Pedido p:miLista){
			precio=precio+(p.getCantidad()*p.getProductoBean().getPrecio());
		}
		return precio;
		
	}
	public Producto getProducto(int idProd){
		return em.find(Producto.class,idProd);
	}
	
}
