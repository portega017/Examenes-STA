package pl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean renderTablaComb=true;
	private boolean renderBotonParar=false;

	public AppBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void pulsarBotonInicio() {
		setRenderTablaComb(false);
		setRenderBotonParar(true);
	}
	public void pulsarBotonParar() {
		setRenderBotonParar(false);
		setRenderTablaComb(true);
	}

	public boolean isRenderTablaComb() {
		return renderTablaComb;
	}

	public void setRenderTablaComb(boolean renderTablaComb) {
		this.renderTablaComb = renderTablaComb;
	}

	public boolean isRenderBotonParar() {
		return renderBotonParar;
	}

	public void setRenderBotonParar(boolean renderBotonParar) {
		this.renderBotonParar = renderBotonParar;
	}

}
