package presentacion;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import datos.Grafico;

@XmlRootElement
public class GraficoDatos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grafico grafico=new Grafico();
	private int circulo;
	private int texto;
	
	public Grafico getGrafico() {
		return grafico;
	}
	public void setGrafico(Grafico grafico) {
		this.grafico = grafico;
	}
	public int getCirculo() {
		return circulo;
	}
	public void setCirculo(int circulo) {
		this.circulo = circulo;
	}
	public int getTexto() {
		return texto;
	}
	public void setTexto(int texto) {
		this.texto = texto;
	}


}
