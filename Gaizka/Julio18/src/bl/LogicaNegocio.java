package bl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dl.Author;
import dl.Journal;
import dl.Publication;

@Stateless
@LocalBean
public class LogicaNegocio {

	private final int OK=0;
	private final int Exception=1;
	private final int AuthorExists=2;
	private final int JournalExists=3;
	private final int PublicationExists=4;
	
	
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
	
	public int altaPublicacion(Publication p, int idxRevista, int idxAut) {
		try {
			if(em.createNamedQuery("Publication.findByName").setParameter("nombre", p.getTitle()).getResultList().isEmpty()) {
				if(!em.createNamedQuery("Journal.findById").setParameter("id", idxRevista).getResultList().isEmpty()) {
					if(!em.createNamedQuery("Author.findById").setParameter("id", idxAut).getResultList().isEmpty()) {
					p.setJournalBean((Journal) em.createNamedQuery("Journal.findById").setParameter("id", idxRevista).getSingleResult());
					em.persist(p);
					return OK;
					}else {
						return Exception;
					}
				}else {
					return Exception;
				}
			}else {
				return PublicationExists;
			}
		}catch(Exception ex) {
			System.err.println(ex.getCause());
			return Exception;
		}	
	}
}
