package metrica6.artik.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.dao.BypassDao;
import metrica6.artik.model.dao.DaoException;

public class BypassDaoImplH extends CustomHibernateDaoSupport implements BypassDao {

	@Override
	public List<Bypass> findAllAvailable() throws DaoException {
		List<Bypass> res = new ArrayList<Bypass>();
		
		try{					
			String hql = "FROM Bypass";
			res = this.getHibernateTemplate().find(hql);
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	@Override
	public Bypass findbyId(Integer id) throws DaoException {
		Bypass aux= new Bypass();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUpdate(Bypass bypass) throws DaoException {
		try{
			this.getHibernateTemplate().saveOrUpdate(bypass);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Integer id) throws DaoException {
		Bypass aux = findbyId(id);
		try{
			this.getHibernateTemplate().delete(aux);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

}
