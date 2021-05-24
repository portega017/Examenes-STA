package datos;

import java.io.File;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Fichero implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ruta="/home/docencia/Desktop/27380/workspace/fichero.xml" ;
	private String ruta2="/home/docencia/Desktop/27380/workspace/cat.xml" ;
	public String getRuta2() {
		return ruta2;
	}
	public void setRuta2(String ruta2) {
		this.ruta2 = ruta2;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public void serializar(Inventario inventario){
		//Inventario inventario = new Inventario();
		JAXBContext jaxbConntext;
		try {
			jaxbConntext=JAXBContext.newInstance(Inventario.class);
			Marshaller m=jaxbConntext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(inventario, new File(ruta));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Inventario deserializar(){
		Inventario inventario =new Inventario();
		File f=new File(ruta);
		if(f.exists()){
			JAXBContext jaxbContext;
			try{
				jaxbContext = JAXBContext.newInstance(Inventario.class);
				Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
				inventario=(Inventario) unmarshaller.unmarshal(f);
				
			}catch (JAXBException e) {
				
				
				// TODO: handle exception
			}
			
		}
		return inventario;
		
	}
	
	
	public ListaCategoria deserializarCategorias(){
		ListaCategoria c =new ListaCategoria();
		File f=new File(ruta2);
		if(f.exists()){
			JAXBContext jaxbContext;
			try{
				jaxbContext = JAXBContext.newInstance(ListaCategoria.class);
				Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
				c=(ListaCategoria) unmarshaller.unmarshal(f);
				
			}catch (JAXBException e) {
				
				
				// TODO: handle exception
			}
			
		}
		return c;
	}
	
	public void serializarCategoria(ListaCategoria c){
		//Inventario inventario = new Inventario();
		JAXBContext jaxbConntext;
		try {
			jaxbConntext=JAXBContext.newInstance(ListaCategoria.class);
			Marshaller m=jaxbConntext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(c, new File(ruta2));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
