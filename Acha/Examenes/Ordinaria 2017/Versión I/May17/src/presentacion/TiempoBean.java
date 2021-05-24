package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;



import datos.Categoria;
import datos.Tarea;
import datos.Tiempo;
import negocio.Servicios;

@Named
@SessionScoped
public class TiempoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private boolean mostrar =false;
	private Tiempo tiempo;
	private String nombre="";
	
	public Tiempo getTiempo() {
		return tiempo;
	}
	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}
	public String bienvenida(){
		String sms= "Bienvenido a la página "+FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		return sms;
	}
	
	public List<Tiempo> getListaTiempo(){
		List<Categoria> cat=miEJB.getListaCategoria();
		List<Tarea> tarea=miEJB.getListaTareas();
		List<Tiempo> tiempoLista=new ArrayList<Tiempo>();

		int catTam=cat.size();
		int TarTam=tarea.size();
				

		for(int i=0;i<catTam;i++){
			for(int j=0;j<TarTam;j++){
			tiempo=new Tiempo();
			tiempo.setCategoria(cat.get(i));
			tiempo.setTarea(tarea.get(j));

			tiempoLista.add(tiempo);
			
			}
			
		}
		return tiempoLista;
	}
	
	public void getSegundosINICIO(int idtarea,int idcat){
		tiempo=new Tiempo();
		Categoria cat=miEJB.getCategoria(idcat);
		Tarea tarea=miEJB.getTarea(idtarea);
		tiempo.setTiempo(miEJB.getSegundos());	
		tiempo.setCategoria(cat);
		tiempo.setTarea(tarea);
		nombre=FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		tiempo.setUsuario(nombre);
		

		setMostrar(true);
	}
	
	public void getSegundosFINAL(){

		long fin=miEJB.getSegundos();
		long total=fin-tiempo.getTiempo();
		tiempo.setTiempo(total);
		miEJB.addTiempo(tiempo);

		setMostrar(false);
	}
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	public List<Tiempo> getTiemposCAT(){
		List<Categoria> cat=miEJB.getListaCategoria();		
		List<Tiempo> miListaAUX=miEJB.getTiempos();
		List<Tiempo> miLista=new ArrayList<Tiempo>();
		String usuario =FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		for(int k=0;k<cat.size();k++){
			
			Tiempo t = new Tiempo();
			t.setCategoria(cat.get(k));
			t.setTiempo(0);
			
			//t.setUsuario(usuario);
			for(int l=0;l<miListaAUX.size();l++){
				String nombre= new String();
				nombre=miListaAUX.get(l).getUsuario();
				if(usuario.equals(nombre)){
					System.out.println(usuario);
				
				if(cat.get(k).getIdCategoria()==miListaAUX.get(l).getCategoria().getIdCategoria()){
					t.setTiempo(t.getTiempo()+miListaAUX.get(l).getTiempo());
				}
				}
				
			}

			miLista.add(t);
		}
		return miLista;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
