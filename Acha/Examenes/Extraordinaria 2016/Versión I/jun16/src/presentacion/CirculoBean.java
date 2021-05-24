package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Circle;
import negocio.Servicios;

@Named
@SessionScoped
public class CirculoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int miSesion=0;
	private Circle circulo=new Circle();
	
	@EJB
	private Servicios miEJB;
	
	public Circle getCirculo() {
		return circulo;
	}
	public void setCirculo(Circle circulo) {
		this.circulo = circulo;
	}
	
	public String addCircle(){
		miEJB.addNuevoCirculo(circulo, miSesion);
		circulo = new Circle();
		return "inicio.xhtml";
	}
	
	public String paginaCircle(int id){
		miSesion=id;
		return "circulo.xhtml";
	}

}
