package metrica6.artik.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import metrica6.artik.model.CiclosMes;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.CiclosMesDao;

public class CiclosMesDaoImplH extends CustomHibernateDaoSupport implements CiclosMesDao {

	@Override
	public List<CiclosMes> findAllAvailable() throws DaoException {
		List<CiclosMes> res = new ArrayList<CiclosMes>();
		
		try{					
			String hql = "FROM CiclosMes";
			res = this.getHibernateTemplate().find(hql);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	@Override
	public CiclosMes findbyId(Integer id) throws DaoException {
		CiclosMes aux= new CiclosMes();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUpdate(CiclosMes mes) throws DaoException {
		try{
			this.getHibernateTemplate().saveOrUpdate(mes);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Integer id) throws DaoException {
		CiclosMes aux = findbyId(id);
		try{
			this.getHibernateTemplate().delete(aux);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}
}
