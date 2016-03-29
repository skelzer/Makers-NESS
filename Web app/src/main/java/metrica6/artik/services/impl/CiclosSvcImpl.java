package metrica6.artik.services.impl;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Ciclos;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.CiclosDao;
import metrica6.artik.services.CiclosSvc;
import metrica6.artik.services.SvcException;

public class CiclosSvcImpl implements CiclosSvc {

private CiclosDao dao;
	
	public CiclosDao getDao() {
		return dao;
	}

	public void setDao(CiclosDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Ciclos> listar() throws SvcException {
		List<Ciclos> res = null;
		
		try {
			res= getDao().findAllAvailable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public Ciclos buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	@Override
	public void insertarModificar(Ciclos Ciclos) throws SvcException {
		try {
			getDao().insertUpdate(Ciclos);
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

	@Override
	public List<Ciclos> buscarBypass(Integer id) throws SvcException {
		try {
			return getDao().findbyIdBypass(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	
	
}
