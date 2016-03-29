package metrica6.artik.services.impl;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.dao.BypassDao;
import metrica6.artik.services.BypassSvc;
import metrica6.artik.services.SvcException;
import metrica6.artik.model.dao.DaoException;


public class BypassSvcImpl implements BypassSvc {
	
	private BypassDao dao;
	
	public BypassDao getDao() {
		return dao;
	}

	public void setDao(BypassDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Bypass> listar() throws SvcException {
		List<Bypass> res = null;
		
		try {
			res= getDao().findAllAvailable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public Bypass buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	@Override
	public void insertarModificar(Bypass Bypass) throws SvcException {
		try {
			getDao().insertUpdate(Bypass);
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
