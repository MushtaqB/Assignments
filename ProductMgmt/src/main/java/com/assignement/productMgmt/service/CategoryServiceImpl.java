package com.assignement.productMgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignement.productMgmt.dao.CategoryDAO;
import com.assignement.productMgmt.model.Category;

/**
 * Service with JPA annotations
 * Hibernate provides JPA implementation
 * @author Mushtaq Ahmed
 *
 */
@Service
public class CategoryServiceImpl extends CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;
	
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public Category findById(Long id) {
		return categoryDAO.findById(Category.class, id);
	}

	@Override
	public Category save(Category entity) {
		return categoryDAO.save(Category.class, entity);
	}

	@Override
	public boolean delete(Category entity) {
		return categoryDAO.delete(Category.class, entity);
	}

	@Override
	public Category updateOrSave(Category entity) {
		return categoryDAO.update(Category.class, entity);
	}

	@Override
	public List<Category> getAllRows() {
		return categoryDAO.getAllRows(Category.class);
	}

	@Override
	public List<Category> findListByField(String fieldName, String value) {
		return categoryDAO.findListByField(Category.class, fieldName, value);
	}

	@Override
	public Category findByField(String fieldName, String value) {
		return categoryDAO.findByField(Category.class, fieldName, value);
	}
	
	
}
