package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Author;

@Named
@RequestScoped
public class AuthorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Author entity = new Author();
	
	public Author getEntity() {
		return entity;
	}
}
