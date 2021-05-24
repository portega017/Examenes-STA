package negocio;

import java.io.Serializable;
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
public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Producto> getListaProductos(){
	List <Producto> misProductos = new ArrayList<>();
		try{
			misProductos=em.createNamedQuery("Producto.findAll").getResultList();
		
		}catch (NoResultException e) {
		// TODO: handle exception
		}
		return misProductos;
	}
	
	public Producto buscarProducto(int id){
		return em.find(Producto.class, id);
	}
	public void addProducto(Producto p){
		em.persist(p);
	}
	
	public void addPedido(Pedido p){
		em.persist(p);
	}
	
	public void cambiarstock(Pedido p){
		Producto productoaux = new Producto();
		productoaux=buscarProducto(p.getProductoBean().getId());
		productoaux.setStock(productoaux.getStock()-p.getCantidad());
		em.persist(productoaux);
	}


}
