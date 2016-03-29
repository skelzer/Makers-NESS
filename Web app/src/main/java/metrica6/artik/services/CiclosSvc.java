package metrica6.artik.services;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Ciclos;

public interface CiclosSvc {
	
	public List<Ciclos> listar() throws SvcException;

	public Ciclos buscar(Integer id) throws SvcException;

	public void insertarModificar(Ciclos Ciclos) throws SvcException;
	
	public void eliminar(Integer id) throws SvcException;
	
	public List<Ciclos> buscarBypass(Integer id) throws SvcException;


}
