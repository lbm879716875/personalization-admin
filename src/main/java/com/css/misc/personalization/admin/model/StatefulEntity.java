package com.css.misc.personalization.admin.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class StatefulEntity extends BaseEntity{
	@Column(name="STAT_CDE")
	private String statCde;

	public String getStatCde() {
		return statCde;
	}

	public void setStatCde(String statCde) {
		this.statCde = statCde;
	}
	

}
