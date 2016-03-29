package metrica6.artik.services.impl;

import java.util.List;


import metrica6.artik.model.CiclosMes;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.CiclosMesDao;
import metrica6.artik.services.CiclosMesSvc;
import metrica6.artik.services.SvcException;

public class CiclosMesSvcImpl implements CiclosMesSvc {

private CiclosMesDao dao;
	
	public CiclosMesDao getDao() {
		return dao;
	}

	public void setDao(CiclosMesDao dao) {
		this.dao = dao;
	}

	@Override
	public List<CiclosMes> listar() throws SvcException {
		List<CiclosMes> res = null;
		
		try {
			res= getDao().findAllAvailable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public CiclosMes buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	@Override
	public void insertarModificar(CiclosMes mes) throws SvcException {
		try {
			getDao().insertUpdate(mes);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}
	
	@Override
	public void eliminar(Integer id) throws SvcException {
		try {
			getDao().remove(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}
	

}
