package metrica6.artik.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import metrica6.artik.model.Mes;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.MesDao;

public class MesDaoImplH extends CustomHibernateDaoSupport implements MesDao {

	@Override
	public List<Mes> findAllAvailable() throws DaoException {
		List<Mes> res = new ArrayList<Mes>();
		
		try{					
			String hql = "FROM Mes";
			res = this.getHibernateTemplate().find(hql);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	@Override
	public Mes findbyId(Integer id) throws DaoException {
		Mes aux= new Mes();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUpdate(Mes mes) throws DaoException {
		try{
			this.getHibernateTemplate().saveOrUpdate(mes);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Integer id) throws DaoException {
		Mes aux = findbyId(id);
		try{
			this.getHibernateTemplate().delete(aux);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}
}
