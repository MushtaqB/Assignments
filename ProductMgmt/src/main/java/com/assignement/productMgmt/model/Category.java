package com.assignement.productMgmt.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Table(name="TBL_PRODUCTMGMT_CATEGORIES")
public class Category  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152331661898753938L;

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

	@Id
	@Column(name = "ID", nullable = false, precision = 22)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Product> getProductLists() {
		return productLists;
	}

	public void setProductLists(Set<Product> productLists) {
		this.productLists = productLists;
	}

	public long getAisleId() {
		return aisleId;
	}

	public void setAisleId(long aisleId) {
		this.aisleId = aisleId;
	}

	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	
	@ManyToOne(targetEntity = Department.class, optional = false)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	private Department department;
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy="category")
	private Set<Product> productLists;
	
	@Column(name = "AISLE_ID", nullable = true)
	private long aisleId;
	
	
}
