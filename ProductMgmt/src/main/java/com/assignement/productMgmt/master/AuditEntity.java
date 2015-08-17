package com.assignement.productMgmt.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * 
 * Super Entity for all Entities
 * For auditing of the tables and data
 * @author Mushtaq Ahmed
 *
 */
@MappedSuperclass
public abstract class AuditEntity implements Serializable{
	
	private static final long serialVersionUID = -8462025212005411277L;
	
	@Version
	@Column(name = "VERSION", precision = 38, scale = 0)
	private long version;
	
	@Column(name = "CREATED_ON", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;

	@Column(name = "CREATED_BY", nullable = true, length = 50)
	private long createdBy;

	@Column(name = "MODIFIED_ON", nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime modifiedOn;

	@Column(name = "MODIFIED_BY", nullable = true, length = 50)
	private long modifiedBy;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(DateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	
}
