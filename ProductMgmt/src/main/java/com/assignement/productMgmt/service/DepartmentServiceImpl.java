package com.assignement.productMgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignement.productMgmt.dao.CategoryDAO;
import com.assignement.productMgmt.dao.DepartmentDAO;
import com.assignement.productMgmt.model.Department;

/**
 * Service with JPA annotations
 * Hibernate provides JPA implementation
 * @author Mushtaq Ahmed
 *
 */
@Service
public class DepartmentServiceImpl extends DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;
	
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	@Override
	public Department findById(Long id) {
		return departmentDAO.findById(Department.class, id);
	}

	@Override
	public Department save(Department entity) {
		return departmentDAO.save(Department.class, entity);
	}

	@Override
	public boolean delete(Department entity) {
		return departmentDAO.delete(Department.class, entity);
	}

	@Override
	public Department updateOrSave(Department entity) {
		return departmentDAO.update(Department.class, entity);
	}

	@Override
	public List<Department> getAllRows() {
		return departmentDAO.getAllRows(Department.class);
	}

	@Override
	public List<Department> findListByField(String fieldName, String value) {
		return departmentDAO.findListByField(Department.class, fieldName, value);
	}

	@Override
	public Department findByField(String fieldName, String value) {
		return departmentDAO.findByField(Department.class, fieldName, value);
	}
	
	
}
