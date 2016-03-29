package metrica6.artik.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import metrica6.artik.model.Ciclos;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.CiclosDao;

public class CiclosDaoImplH extends CustomHibernateDaoSupport implements CiclosDao  {

	@Override
	public List<Ciclos> findAllAvailable() throws DaoException {
		List<Ciclos> res = new ArrayList<Ciclos>();
		
		try{					
			String hql = "FROM Ciclos";
			res = this.getHibernateTemplate().find(hql);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	@Override
	public Ciclos findbyId(Integer id) throws DaoException {
		Ciclos aux= new Ciclos();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUpdate(Ciclos Ciclos) throws DaoException {
		try{
			this.getHibernateTemplate().saveOrUpdate(Ciclos);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Integer id) throws DaoException {
		Ciclos aux = findbyId(id);
		try{
			this.getHibernateTemplate().delete(aux);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	public List<Ciclos> findbyIdBypass(Integer id) throws DaoException {
		List<Ciclos> res = new ArrayList<Ciclos>();
		
		try{					
			String hql = "FROM Ciclos where bypass.id=?";
			res = this.getHibernateTemplate().find(hql,id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	
	
}
