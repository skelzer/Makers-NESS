package metrica6.artik.services.impl;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Datos;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.DatosDao;
import metrica6.artik.services.DatosSvc;
import metrica6.artik.services.SvcException;

public class DatosSvcImpl implements DatosSvc {

private DatosDao dao;
	
	public DatosDao getDao() {
		return dao;
	}

	public void setDao(DatosDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Datos> listar() throws SvcException {
		List<Datos> res = null;
		
		try {
			res= getDao().findAllAvailable();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return res;
	}

	@Override
	public Datos buscar(Integer id) throws SvcException {
		try {
			return getDao().findbyId(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	@Override
	public void insertarModificar(Datos Datos) throws SvcException {
		try {
			getDao().insertUpdate(Datos);
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
	public List<Datos> buscarBypass(Integer id) throws SvcException {
		try {
			return getDao().findbyIdBypass(id);
		} catch (DaoException ex) {
			throw new SvcException(ex);
		}
	}

	
	
}
