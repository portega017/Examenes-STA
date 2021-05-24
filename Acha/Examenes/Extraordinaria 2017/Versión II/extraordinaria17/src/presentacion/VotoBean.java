package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import datos.Evento;
import datos.Usuario;
import datos.Voto;
import negocio.Servicios;

@Named
@SessionScoped
public class VotoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private boolean mostrardias = false;
	private boolean mostrarTabla = true;
	private Voto voto = new Voto();
	private boolean viernes = false;
	private boolean sabado = false;
	private boolean domingo = false;
	private Usuario usuario = new Usuario();
	private String[] mensaje = { "introduzca los datos", "Usuario Incorrecto", "Contraseña Incorrecta",
			"Ya participo en este evento", "Datos añadidos correctamente" };
	
	int i=0;
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	@EJB
	private Servicios miEJB;

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public List<Evento> getListaEventosAbierto() {
		return miEJB.getListaEventosABIERTOS();
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public boolean isMostrardias() {
		return mostrardias;
	}

	public void setMostrardias(boolean mostrardias) {
		this.mostrardias = mostrardias;
	}

	public void pulsarBotondias(int id) {
		mostrardias = true;
		idEvento = id;
		mostrarTabla = false;
	}

	public boolean isViernes() {
		return viernes;
	}

	public void setViernes(boolean viernes) {
		this.viernes = viernes;
	}

	public boolean isSabado() {
		return sabado;
	}

	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}

	public boolean isDomingo() {
		return domingo;
	}

	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String[] getMensaje() {
		return mensaje;
	}

	public void setMensaje(String[] mensaje) {
		this.mensaje = mensaje;
	}
/*
	public void guardarDias() {
		i=miEJB.guardarVotosBD(idEvento, usuario.getDni(), usuario.getContraseña(), viernes, sabado, domingo);
		usuario=new Usuario();

	}*/
	public String mostrarMensajeINFO(){
		return mensaje[i];
	}
	
	public void empezarDeCero(){
		mostrardias=false;
		mostrarTabla=true;
	}
	
	public int guardarVotosBD(){
		
		Usuario usu = miEJB.getUsuario(usuario.getDni());
		if(usu == null){
			i=1;
		}else if(usu.getContraseña().equals(usuario.getContraseña())==false){
			i=2;
		}else {
			Evento ev =miEJB.getEvento(idEvento);
			
			
				List<Voto> misVotos = new ArrayList<>();
				misVotos= miEJB.getListaVotosUSUARIOEVENTO(usu, ev);
						if(misVotos.isEmpty()==false){
					i=3;
				}else
			 {
				i=4;
				Voto v = new Voto();
				v.setDia("Viernes");
				v.setDisponiblidad(viernes);
				v.setUsuario(usu);
				v.setEvento(ev);
				ClientBuilder.newClient()
				.target("http://localhost:8080/extraordinaria17/rest/servicios/addVoto")
				.request(MediaType.APPLICATION_XML).post(Entity.xml(v));
				//miEJB.addVoto(v);
				
				v = new Voto();
				v.setDia("Sabado");
				v.setDisponiblidad(sabado);
				v.setUsuario(usu);
				v.setEvento(ev);
				ClientBuilder.newClient().
				target("http://localhost:8080/extraordinaria17/rest/servicios/addVoto")
				.request(MediaType.APPLICATION_XML).post(Entity.xml(v));
				
				//miEJB.addVoto(v);
				
				v = new Voto();
				v.setDia("Domingo");
				v.setDisponiblidad(domingo);
				v.setUsuario(usu);
				v.setEvento(ev);
				ClientBuilder.newClient()
				.target("http://localhost:8080/extraordinaria17/rest/servicios/addVoto")
				.request(MediaType.APPLICATION_XML).post(Entity.xml(v));
				
				//miEJB.addVoto(v);
			}
		}
		
		
		
		return i;
	}
	
	


}
