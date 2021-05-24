package negocio;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datos.Categoria;
import datos.Fichero;
import datos.Inventario;
import datos.ListaCategoria;
import datos.Producto;


@Path("servicios")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
@LocalBean
@Singleton
public class Rest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@GET
	@Path("getLista")
	@Produces(MediaType.APPLICATION_XML)
	public Inventario getListaProductos(){
		Fichero f = new Fichero();
		Inventario inventario= f.deserializar();
		return inventario;
				
	}
	@POST
	@Path("setLista")
	@Consumes(MediaType.APPLICATION_XML)
	public void guardarProductos(Producto p){
		Fichero f = new Fichero();
		Inventario inventario=new Inventario();
		inventario =f.deserializar();
		inventario.getInventario().add(p);
		f.serializar(inventario);
	}

	
	@GET
	@Path("getCategorias")
	@Produces(MediaType.APPLICATION_XML)
	public ListaCategoria getListaCategoria(){
		Fichero f = new Fichero();
		ListaCategoria c= f.deserializarCategorias();
		return c;
				
	}
	
	@POST
	@Path("setListaCat")
	@Consumes(MediaType.APPLICATION_XML)
	public void guardarProductos(Categoria p){
		Fichero f = new Fichero();
		ListaCategoria inventario=new ListaCategoria();
		inventario =f.deserializarCategorias();
		inventario.getMisCategorias().add(p);
		f.serializarCategoria(inventario);
	}

	
	
	
}
