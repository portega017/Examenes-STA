package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Journal database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Journal.findAll", query="SELECT j FROM Journal j"),
	@NamedQuery(name="Journal.findById", query="SELECT j FROM Journal j WHERE j.id = :id"),
	@NamedQuery(name="Journal.findByName", query="SELECT j FROM Journal j WHERE j.name = :nombre")
})
public class Journal implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String abbr;
	private String name;

	public Journal() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}