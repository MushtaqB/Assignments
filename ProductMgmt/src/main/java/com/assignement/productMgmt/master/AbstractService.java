package com.assignement.productMgmt.master;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Generic Abstract Service class
 * Super class for all Service Classes
 * @author Mushtaq Ahmed
 *
 * @param <T>
 */
@Transactional
public abstract class AbstractService<T> {
	
	//abstract public T findById(T entity);
	abstract public T findById(Long id);
	abstract public T save(T entity);
	abstract public boolean delete(T entity);
	abstract public T updateOrSave(T entity);
	abstract public List<T> getAllRows();
	abstract public List<T> findListByField(String fieldName, String value);
	abstract public T findByField(String fieldName, String value);
}
