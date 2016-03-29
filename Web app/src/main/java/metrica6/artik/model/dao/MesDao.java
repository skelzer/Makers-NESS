package metrica6.artik.model.dao;

import java.util.List;

import metrica6.artik.model.Mes;

public interface MesDao {

	public List<Mes> findAllAvailable() throws DaoException;

	public Mes findbyId(Integer id) throws DaoException;
	
	public void remove(Integer id) throws DaoException;

	public void insertUpdate(Mes mes) throws DaoException;
	
}
