package metrica6.artik.services;

import java.util.List;

import metrica6.artik.model.Mes;


public interface MesSvc {

	public List<Mes> listar() throws SvcException;

	public Mes buscar(Integer id) throws SvcException;

	public void insertarModificar(Mes mes) throws SvcException;
	
	public void eliminar(Integer id) throws SvcException;
}
