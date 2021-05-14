package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Rect;
import negocio.Servicios;

@Named
@RequestScoped
public class RectanguloBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Rect rectangulo = new Rect();
	public Rect getRectangulo() {
		return rectangulo;
	}
	public void setRectangulo(Rect rectangulo) {
		this.rectangulo = rectangulo;
	}
	
	public String addRect(){
		miEJB.addRectangulo(rectangulo);
		
		return "admin";
	}

}
