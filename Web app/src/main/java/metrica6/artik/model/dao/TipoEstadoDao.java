package metrica6.artik.model.dao;

import metrica6.artik.model.TipoEstado;

public interface TipoEstadoDao {

	public TipoEstado findbyId(Integer id) throws DaoException;
	
}
