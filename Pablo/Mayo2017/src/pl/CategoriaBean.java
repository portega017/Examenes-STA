package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Categoria;

@Named
@RequestScoped
public class CategoriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaBean() {
		// TODO Auto-generated constructor stub
	}
	private Categoria categoria = new Categoria();
	private int codError;
	private String respAlta;
	private String respBaja;
	private boolean respRenderA=false;
	private boolean respRenderB=false;

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@EJB
	private Servicios miEjb = new Servicios();
	
	public void altaCategoria() {
		setCodError(miEjb.altaCategoria(categoria));
		if(getCodError()==0) {
			setRespAlta("Categoría dada de alta correctamente.");
		}else if (getCodError()==2) {
			setRespAlta("La categoría ya existe.");
		}
		setRespRenderA(true);
		
	}
	public void bajaCategoria(Categoria c) {
		setCodError(miEjb.borrarCategoria(c.getIdCategorias()));
		if(getCodError()==0) {
			setRespBaja("La Categoría no existe");
		}else if (getCodError()==2) {
			setRespBaja("Categoría borrada Correctamente");
		}
		setRespRenderB(true);
	}
	public int getCodError() {
		return codError;
	}
	public void setCodError(int codError) {
		this.codError = codError;
	}
	public String getRespAlta() {
		return respAlta;
	}
	public void setRespAlta(String resp) {
		this.respAlta = resp;
	}
	public boolean isRespRenderA() {
		return respRenderA;
	}
	public void setRespRenderA(boolean respRender) {
		this.respRenderA = respRender;
	}
	public String getRespBaja() {
		return respBaja;
	}
	public void setRespBaja(String respBaja) {
		this.respBaja = respBaja;
	}
	public boolean isRespRenderB() {
		return respRenderB;
	}
	public void setRespRenderB(boolean respRenderB) {
		this.respRenderB = respRenderB;
	}
	public List<Categoria> getListaCategorias(){
		return miEjb.getListadoCategorias();
		
	}
}
