package com.assignement.productMgmt.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.NotEmpty;

import com.assignement.productMgmt.model.Department;
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Mushtaq Ahmed
 *
 */
public class ProductModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3241187826743375345L;
	private long id;
	
	private String name;
	
	private String desc;
	
	private long price;
	
	private Boolean available;
	
	private long categoryId;
	
	private List<Category> categoriesList;
	
	private List<Department> departmentlist;
	
	private Map<Boolean,String> availableMap;
	
	public Map<Boolean,String> getAvailableMap() {
		this.availableMap= new HashMap<Boolean,String >();
		availableMap.put(true,"Yes" );
		availableMap.put(false,"No" );
		return availableMap;
	}

	public void setAvailableMap(Map<Boolean,String> availableMap) {
		this.availableMap = availableMap;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	private long departmentId;

	public List<Department> getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(List<Department> departmentlist) {
		this.departmentlist = departmentlist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public List<Category> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<Category> categoriesList) {
		this.categoriesList = categoriesList;
	}
	
	
	
}
