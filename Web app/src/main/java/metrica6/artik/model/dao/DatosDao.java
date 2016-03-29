package metrica6.artik.model.dao;

import java.util.List;

import metrica6.artik.model.Datos;


public interface DatosDao {

	public List<Datos> findAllAvailable() throws DaoException;

	public Datos findbyId(Integer id) throws DaoException;
	
	public void remove(Integer id) throws DaoException;

	public void insertUpdate(Datos datos) throws DaoException;
	
	public List<Datos> findbyIdBypass(Integer id) throws DaoException;
	


}
