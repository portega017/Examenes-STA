package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Journal;

@Named
@RequestScoped
public class JournalBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Journal entity = new Journal();
	
	public Journal getEntity() {
		return entity;
	}
}
