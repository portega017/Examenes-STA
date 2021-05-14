package presentacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Alumno;
import datos.Nota;
import negocio.Servicios;

@Named
@RequestScoped
public class NotaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nota nota=new Nota();
	private boolean ok=false;
	@EJB
	private Servicios miEJB;
	
	public Nota getNota() {
		return nota;
	}
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	public boolean addNota(String dni, int idAsig){
		miEJB.addNota(dni, idAsig, nota.getNota());
		ok=true;
		return ok;
	}
	
	public String muestraError(String dni,int id){
		String [] misErrores={"DNI INCORRECTO","ID ASIGNATURA INCORRECTO","DNI E ID INCORRECTOS","DATOS CORRECTOS"};
		int i=3;
		boolean alumno=miEJB.dniCorrecto(dni);
		boolean asignatura=miEJB.idAsigCorrecto(id);
		if(alumno == false && asignatura==false){
			i=2;
		}else if(alumno == true && asignatura==false){
			i=1;
		}else if(alumno == false && asignatura==true){
			i=0;
		}
		return misErrores[i];
		
	}
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean correcto) {
		this.ok = correcto;
	}
	
	
	public List<Nota> getListaNotas(){
		return miEJB.getListaNotas();
	}
	
	public void borrarNota(int id){
		miEJB.borrarNota(id);
	}
	
	
}
