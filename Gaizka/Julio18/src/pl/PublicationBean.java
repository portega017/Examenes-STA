package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Publication;

@Named
@RequestScoped
public class PublicationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Publication entity = new Publication();
	public Publication getEntity() {
		return entity;
	}
		

	
}
