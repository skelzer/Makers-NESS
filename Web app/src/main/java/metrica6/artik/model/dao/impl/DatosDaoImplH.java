package metrica6.artik.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Datos;
import metrica6.artik.model.dao.DaoException;
import metrica6.artik.model.dao.DatosDao;

public class DatosDaoImplH extends CustomHibernateDaoSupport implements DatosDao  {

	@Override
	public List<Datos> findAllAvailable() throws DaoException {
		List<Datos> res = new ArrayList<Datos>();
		
		try{					
			String hql = "FROM Datos";
			res = this.getHibernateTemplate().find(hql);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	@Override
	public Datos findbyId(Integer id) throws DaoException {
		Datos aux= new Datos();
		
		try{					
			
			aux = this.getHibernateTemplate().get(aux.getClass(), id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}

		return aux;
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertUpdate(Datos datos) throws DaoException {
		try{
			this.getHibernateTemplate().saveOrUpdate(datos);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Integer id) throws DaoException {
		Datos aux = findbyId(id);
		try{
			this.getHibernateTemplate().delete(aux);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
	}

	@Override
	public List<Datos> findbyIdBypass(Integer id) throws DaoException {
		List<Datos> res = new ArrayList<Datos>();
		
		try{					
			String hql = "FROM Datos where bypass.id=?";
			res = this.getHibernateTemplate().find(hql,id);			
		}catch (Exception ex){
			throw new DaoException(ex);
		}
		
		return res;
	}

	
	
}
