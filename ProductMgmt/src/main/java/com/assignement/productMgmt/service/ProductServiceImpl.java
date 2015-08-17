package com.assignement.productMgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignement.productMgmt.dao.DepartmentDAO;
import com.assignement.productMgmt.dao.ProductDAO;
import com.assignement.productMgmt.model.Product;

/**
 * Service with JPA annotations
 * Hibernate provides JPA implementation
 * @author Mushtaq Ahmed
 *
 */
@Service
public class ProductServiceImpl extends ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public Product findById(Long id) {
		return productDAO.findById(Product.class, id);
	}

	@Override
	public Product save(Product entity) {
		return productDAO.save(Product.class, entity);
	}

	@Override
	public boolean delete(Product entity) {
		return productDAO.delete(Product.class, entity);
	}

	@Override
	public Product updateOrSave(Product entity) {
		return productDAO.update(Product.class, entity);
	}

	@Override
	public List<Product> getAllRows() {
		return productDAO.getAllRows(Product.class);
	}

	@Override
	public List<Product> findListByField(String fieldName, String value) {
		return productDAO.findListByField(Product.class, fieldName, value);
	}

	@Override
	public Product findByField(String fieldName, String value) {
		return productDAO.findByField(Product.class, fieldName, value);
	}
	
	
}
