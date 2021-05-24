package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Usuario;
import negocio.Servicios;

@Named
@SessionScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario=new Usuario();
	int i;
	String[] mensaje={"Nuevo Usuario Añadido","Evento Añadido Correctamente","ERROR YA ASISTE A ESTE EVENTO","Contraseña Incorrecta"};
	private boolean mostrarSMS=false;
	@EJB
	private Servicios miEJB;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void participar(AsistenciaBean asistencia){
		i =miEJB.participar(usuario.getNombreUsuario(), usuario.getContraseña(), asistencia);
		mostrarSMS=true;
		asistencia = new AsistenciaBean();
	}
	
	public void reiniciar(AsistenciaBean asistencia){
		
		asistencia.setMostrar(false);
		asistencia.setTabla(true);
		asistencia=new AsistenciaBean();
		usuario=new Usuario();
		
		mostrarSMS=false;
	}
	public boolean isMostrarSMS() {
		return mostrarSMS;
	}
	public void setMostrarSMS(boolean mostrarSMS) {
		this.mostrarSMS = mostrarSMS;
	
	
	}
	
	public String mostrarSMS(){
		return  mensaje[i];
	}
	
	
}
