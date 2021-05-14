package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Rect;
import negocio.Servicios;
@Named
@RequestScoped
public class Rectabean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rect recta=new Rect();
	@EJB
	private Servicios miEJB;
	
	
	public Rect getRecta() {
		return recta;
	}
	public void setRecta(Rect recta) {
		this.recta = recta;
	}
	
	public String addRectangulo(){
		miEJB.addRectangulo(recta);
		return "formas.xhtml";
	}

}
