package metrica6.artik.services.impl;

import metrica6.artik.model.TipoEstado;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.TipoEstadoDao;
import metrica6.artik.services.SvcException;
import metrica6.artik.services.TipoEstadoSvc;

public class TipoEstadoSvcImpl implements TipoEstadoSvc {

	private TipoEstadoDao dao;
	
	public TipoEstadoDao getDao() {
		return dao;
	}

	public void setDao(TipoEstadoDao dao) {
		this.dao = dao;
	}
	
	@Override
	public TipoEstado buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
		

	}

}
