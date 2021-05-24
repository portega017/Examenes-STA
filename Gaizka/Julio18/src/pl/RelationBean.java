package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Relation;

@Named
@RequestScoped
public class RelationBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Relation entity = new Relation();

	public Relation getEntity() {
		return entity;
	}
	
	
}
