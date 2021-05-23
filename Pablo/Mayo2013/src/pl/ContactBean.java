package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.Servicios;
import dl.Contacto;

@Named
@SessionScoped
public class ContactBean implements Serializable{

	/**
	 * 
	 */
	
	@EJB
	private Servicios miEJB;
	private Contacto contacto =new Contacto();
	private static final long serialVersionUID = 1L;
	private boolean mostrarTabla=false;
	private boolean mostrarTabla2 = false;
	
	
	public void mostrarTabla() {
		mostrarTabla=true;
		mostrarTabla2=false;
	}
	
	public List<Contacto> getListaContacto(){
		return miEJB.getListado();
	}
	
	public List<Contacto> getListaNombre(){
		return miEJB.getListaPorNombre(contacto.getNombre());
	}
	
	public void mostrarTablaNombre(){
		 mostrarTabla=false;
		 mostrarTabla2=true;
	 }
	
	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public boolean isMostrarTabla2() {
		return mostrarTabla2;
	}

	public void setMostrarTabla2(boolean mostrarTabla2) {
		this.mostrarTabla2 = mostrarTabla2;
	}

	public void addContact() {
		miEJB.addContact(contacto);
		contacto=new Contacto();
		mostrarTabla=false;
	}
	
	public void deleteContact(int id) {
		miEJB.borrarContact(id);
	}
	
	

}
