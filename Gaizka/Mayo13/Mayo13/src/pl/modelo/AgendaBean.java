package pl.modelo;

import javax.ejb.EJB;

import bl.LogicaNegocio;

public class AgendaBean {

	@EJB
	private LogicaNegocio ln = new LogicaNegocio();
	
	
}
