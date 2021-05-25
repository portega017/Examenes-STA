package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Author;
import dl.Journal;
import dl.Publication;
import dl.Relation;

@Stateless
@LocalBean
public class LogicaNegocio {

	private final int OK=0;
	private final int Exception=1;
	private final int AuthorExists=2;
	private final int JournalExists=3;
	
	
	@PersistenceContext
	private EntityManager em;
	
	public int altaAutor(Author a) {
		try {
			if(em.createNamedQuery("Author.findByName").setParameter("nombre", a.getName()).getResultList().isEmpty()) {
				em.persist(a);
				return OK;
			}else {
				return AuthorExists;
			}
		}catch(Exception ex) {
			ex.getCause();
			return Exception;
		}	
	}
	
	public int altaRevista(Journal j) {
		try {
			if(em.createNamedQuery("Journal.findByName").setParameter("nombre", j.getName()).getResultList().isEmpty()) {
				em.persist(j);
				return OK;
			}else {
				return JournalExists;
			}
		}catch(Exception ex) {
			ex.getCause();
			return Exception;
		}	
	}
	
	public List<Journal> getRevistas() {
		return em.createNamedQuery("Journal.findAll", Journal.class).getResultList();
	}
	
	public int altaPublicacion(Publication p, int idxRevista, List<Integer> autList) {
		try {
			if(!em.createNamedQuery("Journal.findById").setParameter("id", idxRevista).getResultList().isEmpty()) {
				p.setJournalBean((Journal) em.createNamedQuery("Journal.findById").setParameter("id", idxRevista).getSingleResult());
				em.persist(p);
				for(int i=0;i<autList.size();i++) {
					anadirAutorAPub(p, autList.get(i));
				}
				return OK;
			}else {
				return Exception;
			}
		}catch(Exception ex) {
			System.err.println(ex.getCause());
			return Exception;
		}	
	}
	
	public int anadirAutorAPub(Publication p, int autId) {
		Relation r = new Relation();
		if(!em.createNamedQuery("Author.findById").setParameter("id", autId).getResultList().isEmpty()) {	
			Author aut = (Author) em.createNamedQuery("Author.findById").setParameter("id", autId).getSingleResult();
			r.setAuthorBean((Author) aut);
			r.setPublicationBean((Publication) p);
			em.persist(r);
			return OK;
		}else {
			return Exception;
		}
	}
	
	public List<Author> getAutor() {
		return em.createNamedQuery("Author.findAll", Author.class).getResultList();
	}

}










