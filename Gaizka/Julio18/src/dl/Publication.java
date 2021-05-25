package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Publication database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Publication.findAll", query="SELECT p FROM Publication p"),
	@NamedQuery(name="Publication.findById", query="SELECT p FROM Publication p WHERE p.id = :id"),
	@NamedQuery(name="Publication.findByName", query="SELECT p FROM Publication p WHERE p.title = :nombre")
})
public class Publication implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private int year;
	private Journal journalBean;

	public Publication() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	//uni-directional many-to-one association to Journal
	@ManyToOne
	@JoinColumn(name="journal")
	public Journal getJournalBean() {
		return this.journalBean;
	}

	public void setJournalBean(Journal journalBean) {
		this.journalBean = journalBean;
	}

}