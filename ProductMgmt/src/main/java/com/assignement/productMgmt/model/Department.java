package com.assignement.productMgmt.model;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
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

@Table(name="TBL_PRODUCTMGMT_DEPARTMENTS")
public class Department implements java.io.Serializable {

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
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="department")
	private Set<Category> categoriesLists;
	
	public Set<Category> getCategoriesLists() {
		return categoriesLists;
	}

	public void setCategoriesLists(Set<Category> categoriesLists) {
		this.categoriesLists = categoriesLists;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	@Column(name = "LOCATION_ID", nullable = true)
	private long locationId;
	
}
