package presentacion;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datos.Pedido;
import negocio.Servicios;

@Named
@RequestScoped
public class PedidoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido =new Pedido();
	@EJB
	private Servicios miEJB;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
