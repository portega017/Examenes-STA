package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import datos.Text;
import negocio.Servicios;

@Named
@SessionScoped
public class TextoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private Servicios miEJB;
	private Text texto=new Text();
	int miSesion=0;
	
	public Text getTexto() {
		return texto;
	}
	public void setTexto(Text texto) {
		this.texto = texto;
	}
	
	public String addTexto(){
		miEJB.addNuevoTexto(texto, miSesion);
		texto=new Text();
		return "inicio.xhtml";
	}
	
	
	public String paginaText(int id){
		miSesion=id;
		return "texto.xhtml";
	}
	
	
}
