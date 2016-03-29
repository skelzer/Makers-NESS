package metrica6.artik.services;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Datos;

public interface DatosSvc {
	
	public List<Datos> listar() throws SvcException;

	public Datos buscar(Integer id) throws SvcException;

	public void insertarModificar(Datos datos) throws SvcException;
	
	public void eliminar(Integer id) throws SvcException;
	
	public List<Datos> buscarBypass(Integer id) throws SvcException;


}
