package metrica6.artik.services;

import java.util.List;

import metrica6.artik.model.CiclosMes;


public interface CiclosMesSvc {

	public List<CiclosMes> listar() throws SvcException;

	public CiclosMes buscar(Integer id) throws SvcException;

	public void insertarModificar(CiclosMes mes) throws SvcException;
	
	public void eliminar(Integer id) throws SvcException;
}
