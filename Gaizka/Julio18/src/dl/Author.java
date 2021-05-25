package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Author database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a"),
	@NamedQuery(name="Author.findById", query="SELECT a FROM Author a WHERE a.id = :id"),
	@NamedQuery(name="Author.findByName", query="SELECT a FROM Author a WHERE a.name = :nombre")
})

public class Author implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	
	public Author() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Transient
	public String getNameAbrev() {
		return name.substring(0,1).concat(".");
	}
}