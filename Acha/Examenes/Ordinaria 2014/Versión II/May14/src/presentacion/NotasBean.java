package presentacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Alumno;
import datos.Nota;
import negocio.Servicios;
@Named
@RequestScoped
public class NotasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dni;
	private boolean mostrarNotas=false;
	private String[] misMensajes={"INTRODUZCA LAS NOTAS","DNI INCORRECTO","NO HAY NOTAS DISPONIBLES","TODAS LAS NOTAS:"};
	@EJB
	private Servicios miEJB;
	int i=0;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void getNotas(){
		
	}
	public String[] getMisMensajes() {
		return misMensajes;
	}
	public void setMisMensajes(String[] misMensajes) {
		this.misMensajes = misMensajes;
	}
	
	public List<Nota> getListaNotas(){
		List<Nota> misNotas = new ArrayList<>();
		Alumno a = miEJB.getMiAlumno(dni);
		if(a==null){
			i=1;
		}else{
			misNotas=miEJB.getListaNotasALUMNO(dni);
			if(misNotas.isEmpty()==true){
				i=2;
			}else{
				i=3;
				mostrarNotas=true;
			}
		}
		return misNotas;
	}
	public boolean isMostrarNotas() {
		return mostrarNotas;
	}
	public void setMostrarNotas(boolean mostrarNotas) {
		this.mostrarNotas = mostrarNotas;
	}
	
	
	public String verMIMENSAJE(){
		return misMensajes[i];
	}
	

}
