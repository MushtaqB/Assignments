package com.assignement.productMgmt.model;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;


/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author Mushtaq Ahmed
 *
 */
@Entity

@Table(name="TBL_PRODUCTMGMT_PRODUCTS")
public class Product  implements java.io.Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6401607733271213850L;
	
	@Id
	@Column(name = "ID", nullable = false, precision = 22)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	@Column(name = "PRICE", nullable = true)
	private Long price;
	
	@Column(name = "AVAILABLE", nullable = true)
	private Boolean available;
	

	@ManyToOne(targetEntity = Department.class, optional = false)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	private Department department;
	
	
	@ManyToOne(targetEntity = Category.class, optional = false)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	

}
