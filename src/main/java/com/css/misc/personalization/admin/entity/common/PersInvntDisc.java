package com.css.misc.personalization.admin.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.misc.personalization.admin.model.BaseEntity;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;

@Entity
@Table(name="PERS_INVNT_DISC")
@IdClass(PersInvntDiscPK.class)
public class PersInvntDisc extends BaseEntity{
	@Id
	@Column(name="INVNT_ID")
	private String invntId;
	@Id
	@Column(name="REGN_REF_CDE")
	private String regnRefCde;
	@Id
	@Column(name="DISC_TAG")
	private String discTag;
	@Id
	@Column(name="START_DTE")
	private Date startDte;
	@Column(name="END_DTE")
	private Date endDte;
	
	

	public PersInvntDisc() {
		super();
	}
	public PersInvntDisc(String invntId, String regnRefCde, String discTag, Date startDte, Date endDte,String lastModEmplNbr) {
		super();
		this.invntId = invntId;
		this.regnRefCde = regnRefCde;
		this.discTag = discTag;
		this.startDte = startDte;
		this.endDte = endDte;
		this.setLastModEmplNbr(lastModEmplNbr);
	}
	
	public String getInvntId() {
		return invntId;
	}
	public void setInvntId(String invntId) {
		this.invntId = invntId;
	}
	public String getRegnRefCde() {
		return regnRefCde;
	}
	public void setRegnRefCde(String regnRefCde) {
		this.regnRefCde = regnRefCde;
	}
	public String getDiscTag() {
		return discTag;
	}
	public void setDiscTag(String discTag) {
		this.discTag = discTag;
	}
	public Date getStartDte() {
		return startDte;
	}
	public void setStartDte(Date startDte) {
		this.startDte = startDte;
	}
	public Date getEndDte() {
		return endDte;
	}
	public void setEndDte(Date endDte) {
		this.endDte = endDte;
	}

	
	
}
