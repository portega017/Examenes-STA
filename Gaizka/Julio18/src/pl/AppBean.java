package pl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;
import dl.Author;
import dl.Journal;
import dl.Publication;

@Named
@ViewScoped
public class AppBean implements Serializable {

	@EJB
	private LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	
	private int codErrorAut;
	private boolean renderAutResp;
	private String autResp;
	private List<Journal> lj;
	private int idxAut;
	
	private int codErrorRev;
	private boolean renderRevResp;
	private String revResp;
	private List<Author> la;
	private int idxRev;
	
	private int codErrorPub;
	private boolean renderPubResp;
	private String pubResp;
	
	private List<Integer> lafp;
	
	public void altaAutor(Author a) {
		setCodErrorAut(ln.altaAutor(a));
		if(getCodErrorAut() == 0) {
			setAutResp("Contacto dado de alta satisfactoriamente.");
		}else {
			setAutResp("El contacto que quiere dar de alta ya existe.");
		}
		setRenderAutResp(true);
		la = null;
	}
	
	public void altaRevista(Journal j) {
		setCodErrorAut(ln.altaRevista(j));
		if(getCodErrorAut() == 0) {
			setRevResp("Revista dada de alta satisfactoriamente.");
		}else {
			setRevResp("La revista que quiere dar de alta ya existe.");
		}
		setRenderRevResp(true);
		lj = null;
	}
	
	public List<Journal> getRevista() {
		if(lj == null) {
			lj = ln.getRevistas();
		}
		return lj;
	}
	
	public void altaPublicacion(Publication p) {
		setCodErrorPub(ln.altaPublicacion(p,idxRev,lafp));
		if(getCodErrorPub() == 0) {
			setPubResp("Publicacion dada de alta satisfactoriamente.");
		}else {
			setPubResp("La publicacion que quiere dar de alta ya existe.");
		}
		setRenderPubResp(true);
	}
	
	public List<Author> getAutor(){
		if(la == null) {
			la = ln.getAutor();
		}
		return la;
	}

	public void authorToList(int autId) {
		lafp.add(autId);
	}

	public int getCodErrorAut() {
		return codErrorAut;
	}

	public void setCodErrorAut(int codError) {
		this.codErrorAut = codError;
	}

	public String getAutResp() {
		return autResp;
	}

	public void setAutResp(String autResp) {
		this.autResp = autResp;
	}

	public boolean isRenderAutResp() {
		return renderAutResp;
	}

	public void setRenderAutResp(boolean renderAutResp) {
		this.renderAutResp = renderAutResp;
	}

	public int getCodErrorRev() {
		return codErrorRev;
	}

	public void setCodErrorRev(int codErrorRev) {
		this.codErrorRev = codErrorRev;
	}

	public boolean isRenderRevResp() {
		return renderRevResp;
	}

	public void setRenderRevResp(boolean renderRevResp) {
		this.renderRevResp = renderRevResp;
	}

	public String getRevResp() {
		return revResp;
	}

	public void setRevResp(String revResp) {
		this.revResp = revResp;
	}

	public int getIdxRev() {
		return idxRev;
	}

	public void setIdxRev(int idxRev) {
		this.idxRev = idxRev;
	}

	public int getCodErrorPub() {
		return codErrorPub;
	}

	public void setCodErrorPub(int codErrorPub) {
		this.codErrorPub = codErrorPub;
	}

	public boolean isRenderPubResp() {
		return renderPubResp;
	}

	public void setRenderPubResp(boolean renderPubResp) {
		this.renderPubResp = renderPubResp;
	}

	public String getPubResp() {
		return pubResp;
	}

	public void setPubResp(String pubResp) {
		this.pubResp = pubResp;
	}

	public int getIdxAut() {
		return idxAut;
	}

	public void setIdxAut(int idxAut) {
		this.idxAut = idxAut;
	}
}
