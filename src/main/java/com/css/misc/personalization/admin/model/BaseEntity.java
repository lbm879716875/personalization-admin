package com.css.misc.personalization.admin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	@Column(name="LAST_MOD_EMPL_NBR")
	private String lastModEmplNbr;
	@Column(name="LAST_MOD_DTE")
	private Date lastModDte;
	public String getLastModEmplNbr() {
		return lastModEmplNbr;
	}
	public void setLastModEmplNbr(String lastModEmplNbr) {
		this.lastModEmplNbr = lastModEmplNbr;
	}
	public Date getLastModDte() {
		return lastModDte;
	}
	public void setLastModDte(Date lastModDte) {
		this.lastModDte = lastModDte;
	}
	
}
