package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Relation database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r"),
	@NamedQuery(name="Relation.findById", query="SELECT r FROM Relation r WHERE r.id = :id")
})
public class Relation implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Author authorBean;
	private Publication publicationBean;

	public Relation() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	//uni-directional many-to-one association to Author
	@ManyToOne
	@JoinColumn(name="author")
	public Author getAuthorBean() {
		return this.authorBean;
	}

	public void setAuthorBean(Author authorBean) {
		this.authorBean = authorBean;
	}


	//uni-directional many-to-one association to Publication
	@ManyToOne
	@JoinColumn(name="publication")
	public Publication getPublicationBean() {
		return this.publicationBean;
	}

	public void setPublicationBean(Publication publicationBean) {
		this.publicationBean = publicationBean;
	}

}