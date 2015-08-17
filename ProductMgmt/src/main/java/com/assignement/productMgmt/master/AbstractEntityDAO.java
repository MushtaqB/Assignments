package com.assignement.productMgmt.master;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Super Class of all DAO Class
 * Generice Abstract Class 
 * @author Mushtaq Ahmed
 *
 */

@SuppressWarnings("unchecked")
public abstract class AbstractEntityDAO  {

	@Autowired
	private SessionFactory factory;
	
	public void setSessionFactory(SessionFactory sf){
		this.factory = sf;
	}
	public <D> D findById(Class<D> clazz, Long id) {
		Session session = getSession();
		return clazz.cast(session.get(clazz, id));
	}
	/*public <D> D findById(Class<D> clazz, D entity) {
		return findById(clazz, Long.valueOf(entity.hashCode()));
	}*/
	public <D> D findById(D entity) {
		Session session = getSession();
		return (D)session.get(entity.getClass(), entity.hashCode());
	}
	
	public <D> D findByField(Class<D> clazz, String fieldName, String value) {
		List<D> results = findListByField(clazz, fieldName, value);
		if(results == null || results.size() <=0) return null;
		return results.get(0);
	}
	public <D> List<D> findListByField(Class<D> clazz, String fieldName, String value) {
		Session session = getSession();
		StringBuilder sb = new StringBuilder();
		sb.append("from "+clazz.getSimpleName()+" f where f."+fieldName+" = '"+value+"' ");
		Query query = session.createQuery(sb.toString());
		return query.list();
	}
	
	public <D> List<D> getAllRows(Class<D> clazz) {
		return getSession().createQuery("from " + clazz.getSimpleName()).list();
	}
	
	
	public <D> D save(Class<D> clazz, D entity){
		try{
			if(entity != null && entity.hashCode() == -1){
				return create(clazz, entity);
			}
			return update(clazz, entity);
		}catch(StaleObjectStateException s){
			throw new StaleObjectStateException(entity.getClass().getSimpleName(), entity.hashCode());
		}
	}

	public <D> D update(Class<D> clazz, D entity) {
		Session session = getSession();
		session.saveOrUpdate(clazz.getSimpleName(), entity);
		return (D)entity;
	}

	private <D> D create(Class<D> clazz, D entity){
		try{
			//pushAuditInfo((AbstractEntity)entity, entity.);
		}catch(ClassCastException e){
			throw new ClassCastException("Entity must be extended Audit Entity class");
		}
		Session session = getSession();
		session.saveOrUpdate(clazz.getName(), entity);
		return entity;
	}
	
	public boolean delete(Class clazz, Object entity){
		try{
			Session session = getSession();
			/*int ha = entity.hashCode();
			Object obj = findById(clazz, Long.valueOf(ha));
			Object merged = session.merge(obj);*/
			session.delete(clazz.getSimpleName(), entity);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Session getSession() {
		Session session = factory.getCurrentSession();
		return session;
	}

	
	private void pushAuditInfo(AbstractEntity entity, long userId) {
		if (entity.getCreatedOn() == null ||
				entity.getCreatedBy() == 0) {
			entity.setCreatedBy(userId);
			entity.setCreatedOn(new DateTime());
		}

		entity.setModifiedBy(userId);
		entity.setModifiedOn(new DateTime());
	}
	
}
