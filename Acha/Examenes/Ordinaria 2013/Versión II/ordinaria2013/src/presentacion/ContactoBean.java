package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Contacto;
import negocio.Servicios;
@Named
@SessionScoped
public class ContactoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	
	private Contacto contacto=new Contacto();
	private boolean mostrarTabla=false;
	private boolean mostrarTabla2=false;
	
	 public void mostrarTabla(){
		 mostrarTabla=true;
		 mostrarTabla2=false;
	 }
	public List<Contacto> getListaContactosTOTAL(){
		return miEJB.getListaContactosENTERA();
	}
	public List<Contacto> getListaContactosPARCIAL(){
		return miEJB.getListaContactosPARCIAL(contacto.getNombre());
	}
	
	 public void mostrarTablaPARCIAL(){
		 mostrarTabla=false;
		 mostrarTabla2=true;
	 }
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public void borrarContacto(int id){
		miEJB.borrarContacto(id);
	}
	
	public void addContacto(){
		miEJB.addContacto(contacto);
		contacto=new Contacto();
		mostrarTabla=false;
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

}
