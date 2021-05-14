package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Categoria;
import datos.Tarea;
import negocio.Servicios;

@Named
@RequestScoped
public class CategoriaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Categoria categoria=new Categoria();
	

	public void addCategoria(String nombre){
		miEJB.addCategoria(nombre);
	}
	
	public List<Categoria> getListaCategorias(){
		return miEJB.getListaCategoria();
	}
	
	public void borrarCategoriass(int id){
		miEJB.borrarCategoria(id);
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
