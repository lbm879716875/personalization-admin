package com.css.misc.personalization.admin.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.css.misc.personalization.admin.entity.common.PersInvntDisc;

public class PersInvntDiscListRequest extends BaseEntity{
	private String invntId;
	private String regnRefCde;
	private String discTag;
	private Date startDte;
	private Date endDte;
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
	
	public List<PersInvntDisc> toPersInvntDiscList(){
		List<PersInvntDisc> list = new LinkedList<>();
		String[] ids = this.invntId.split(",");
		for(String id:ids) {
			PersInvntDisc disc = new PersInvntDisc(id,this.regnRefCde,this.discTag,this.startDte,this.endDte,this.getLastModEmplNbr());
			disc.setLastModDte(new Date());
			list.add(disc);
		}
		return list;
	}
}
