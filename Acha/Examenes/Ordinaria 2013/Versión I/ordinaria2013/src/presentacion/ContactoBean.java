package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Contacto;
import negocio.Servicios;

@RequestScoped
@Named
public class ContactoBean implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contacto contacto=new Contacto();
	private boolean ok =false;
	private boolean mostrar =false;
	@EJB	
	private Servicios miEJB;
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}
	
	public List<Contacto> getListaContactos(){
		return miEJB.getListaContactos();
	}
	
	public void deleteContacto(int id ){
		miEJB.deleteContacto(id);
	}
	public boolean addContacto(){
		if(contacto.getNombre()!=null){
			contacto.setAlias(contacto.getAlias());
			contacto.setApellidos(contacto.getApellidos());
			contacto.setCorreo(contacto.getCorreo());
			contacto.setNombre(contacto.getNombre());
			contacto.setNumero(contacto.getNumero());
			miEJB.addContacto(contacto);
			ok=true;
		}
		return ok;
		
		
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	public List<Contacto> getContactoNombre(){
		
		List<Contacto> miLista=miEJB.getContactoNOMBRE(contacto.getNombre());
		if(miLista.isEmpty()==false){
			mostrar =true;
		}
		return miLista;
	}
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
}
