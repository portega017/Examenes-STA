package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import presentacion.GraficoDatos;
@XmlRootElement
public class ListaGrafico  {
private List<GraficoDatos> miLista = new ArrayList<GraficoDatos>();
@XmlElement
public List<GraficoDatos> getMiLista() {
	return miLista;
}

public void setMiLista(List<GraficoDatos> miLista) {
	this.miLista = miLista;
}



}
