package com.css.misc.personalization.admin.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.entity.interfaces.WithDb;
import com.css.misc.personalization.admin.model.StatefulEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="PERS_DISC_DESC")
public class PersDiscDesc extends StatefulEntity implements WithDb{
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
	@JsonProperty(access = Access.READ_ONLY)
	private Integer invntCount;
	@JsonProperty(access = Access.READ_ONLY)
	@Transient
	private Db db;
	
	
	
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
	
	public void setStatcDescCZhs(String statcDescCZhs) {
		this.statcDescCZhs = statcDescCZhs;
	}

	public Integer getInvntCount() {
		return invntCount;
	}

	public void setInvntCount(Integer invntCount) {
		this.invntCount = invntCount;
	}

	public Db getDb() {
		return db;
	}
	
	public void setDb(Db db) {
		this.db = db;
	}
}
