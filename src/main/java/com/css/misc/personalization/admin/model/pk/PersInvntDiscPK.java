package com.css.misc.personalization.admin.model.pk;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.misc.personalization.admin.entity.common.PersInvntDisc;

public class PersInvntDiscPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3435525734257411057L;
	private String invntId;
	private String regnRefCde;
	private String discTag;
	@DateTimeFormat(pattern="yyyy-MM-dd" )
	private Date startDte;
	
	
	public PersInvntDiscPK() {
		super();
	}


	public PersInvntDiscPK(String invntId, String regnRefCde, String discTag, Date startDte) {
		super();
		this.invntId = invntId;
		this.regnRefCde = regnRefCde;
		this.discTag = discTag;
		this.startDte = startDte;
	}


	public PersInvntDiscPK(PersInvntDisc pid) {
		this.invntId=pid.getInvntId();
		this.regnRefCde=pid.getRegnRefCde();
		this.discTag=pid.getDiscTag();
		this.startDte=pid.getStartDte();
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
	
	
}
