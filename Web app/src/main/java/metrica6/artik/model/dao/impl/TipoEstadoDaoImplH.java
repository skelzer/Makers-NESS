package metrica6.artik.model.dao.impl;

import metrica6.artik.model.TipoEstado;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.TipoEstadoDao;

public class TipoEstadoDaoImplH extends CustomHibernateDaoSupport implements TipoEstadoDao {

	@Override
	public TipoEstado findbyId(Integer id) throws DaoException {
		TipoEstado aux= new TipoEstado();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

}
