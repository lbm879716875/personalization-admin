package com.css.misc.personalization.admin.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.misc.personalization.admin.model.StatefulEntity;

@Entity
@Table(name="PERS_DISC_DESC")
public class PersDiscDesc extends StatefulEntity{
	@Id
	@Column(name="DISC_TAG")
	private String discTag;

	@Column(name="STATC_DESC")
	private String statcDesc;
	
	@Column(name="STATC_DESC_C")
	private String statcDescC;
	
	@Column(name="STATC_DESC_C_ZHS")
	private String statcDescCZhs;

	@Transient
	private Integer invntCount;
	
	public String getDiscTag() {
		return discTag;
	}

	public void setDiscTag(String discTag) {
		this.discTag = discTag;
	}

	public String getStatcDesc() {
		return statcDesc;
	}

	public void setStatcDesc(String statcDesc) {
		this.statcDesc = statcDesc;
	}

	public String getStatcDescC() {
		return statcDescC;
	}

	public void setStatcDescC(String statcDescC) {
		this.statcDescC = statcDescC;
	}

	public String getStatcDescCZhs() {
		return statcDescCZhs;
	}

	public void setStatcDescCZHS(String statcDescCZhs) {
		this.statcDescCZhs = statcDescCZhs;
	}
	
	
}
