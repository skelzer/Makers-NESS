package metrica6.artik.services.impl;

import java.util.List;


import metrica6.artik.model.Mes;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.MesDao;
import metrica6.artik.services.MesSvc;
import metrica6.artik.services.SvcException;

public class MesSvcImpl implements MesSvc {

private MesDao dao;
	
	public MesDao getDao() {
		return dao;
	}

	public void setDao(MesDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Mes> listar() throws SvcException {
		List<Mes> res = null;
		
		try {
			res= getDao().findAllAvailable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public Mes buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	@Override
	public void insertarModificar(Mes mes) throws SvcException {
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
