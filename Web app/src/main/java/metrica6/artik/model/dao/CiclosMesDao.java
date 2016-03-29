package metrica6.artik.model.dao;

import java.util.List;

import metrica6.artik.model.CiclosMes;



public interface CiclosMesDao {

	public List<CiclosMes> findAllAvailable() throws DaoException;

	public CiclosMes findbyId(Integer id) throws DaoException;
	
	public void remove(Integer id) throws DaoException;

	public void insertUpdate(CiclosMes mes) throws DaoException;
	
}
