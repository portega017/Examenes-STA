package presentacion;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import datos.Categoria;
import datos.Inventario;
import datos.ListaCategoria;
import datos.Producto;
@Named
@SessionScoped
public class InventarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Producto producto = new Producto();
	private Categoria cat= new Categoria();
	public Categoria getCat() {
		return cat;
	}
	public void setCat(Categoria cat) {
		this.cat = cat;
	}
	private List<SelectItem> items;
	private ListaCategoria miCategorias;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int indice;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public List<Producto> getLista(){
	
		Inventario i = ClientBuilder.newClient().target("http://localhost:8080/junio15/rest/servicios/getLista").request(MediaType.APPLICATION_XML).get(Inventario.class);
	return i.getInventario();
	}
	
	public void guardarProducto(){
		producto.setNumref(System.currentTimeMillis());
		System.out.println("aaaa");
		ClientBuilder.newClient().target("http://localhost:8080/junio15/rest/servicios/setLista").request(MediaType.APPLICATION_XML).post(Entity.xml(producto));
		
	}
	
	@PostConstruct
	public  void prepararListaProductos(){
		miCategorias = ClientBuilder.newClient().target("http://localhost:8080/junio15/rest/servicios/getCategorias").request(MediaType.APPLICATION_XML).get(ListaCategoria.class);
		items= new ArrayList<>(miCategorias.getMisCategorias().size());	
		for(Categoria cat:miCategorias.getMisCategorias()){
			items.add(new SelectItem(indice++,cat.getTipo()));
		}indice=0;
	
	
	}
	
	public List<SelectItem> getItems() {
		return items;
	}
	public void setItems(List<SelectItem> items) {
		this.items = items;
	}
	public ListaCategoria getMiCategorias() {
		return miCategorias;
	}
	public void setMiCategorias(ListaCategoria miCategorias) {
		this.miCategorias = miCategorias;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
		producto.setCategoria(miCategorias.getMisCategorias().get(indice));
	}
	
	public void guardarcat(){


		ClientBuilder.newClient().target("http://localhost:8080/junio15/rest/servicios/setListaCat").request(MediaType.APPLICATION_XML).post(Entity.xml(cat));
		
	}
	

}
