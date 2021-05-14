package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Circle;
import negocio.Servicios;
@Named
@RequestScoped
public class CirculoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Circle circulo = new Circle();
	@EJB
	private Servicios miEJB;
	
	public Circle getCirculo() {
		return circulo;
	}
	public void setCirculo(Circle circulo) {
		this.circulo = circulo;
	}
	
	public String addCirculo(){
		miEJB.addCirculo(circulo);


		return "admin";
	}

}
