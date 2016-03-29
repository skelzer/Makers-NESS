package metrica6.artik.model.dao;

import java.util.List;

import metrica6.artik.model.Bypass;


public interface BypassDao {

	public List<Bypass> findAllAvailable() throws DaoException;

	public Bypass findbyId(Integer id) throws DaoException;
	
	public void remove(Integer id) throws DaoException;

	public void insertUpdate(Bypass bypass) throws DaoException;
	
}
