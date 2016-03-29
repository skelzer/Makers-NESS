package metrica6.artik.model.dao;

import java.util.List;


import metrica6.artik.model.Ciclos;

public interface CiclosDao {

	public List<Ciclos> findAllAvailable() throws DaoException;

	public Ciclos findbyId(Integer id) throws DaoException;
	
	public void remove(Integer id) throws DaoException;

	public void insertUpdate(Ciclos ciclos) throws DaoException;
	
	public List<Ciclos> findbyIdBypass(Integer id) throws DaoException;
	


}