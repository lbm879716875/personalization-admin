package com.css.misc.personalization.admin.model.pk;

import java.io.Serializable;
import java.util.Objects;

import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLead;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;

public class EffDteKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7275489279308442840L;
	private String tmplCmpntId;
	private String regnRefCde;
	private String suplrId;
	private Integer seqNbr;
	public EffDteKey() {
		super();
	}
	public EffDteKey(PersTmplCmpntLeadEffDte leadEff) {
		this.tmplCmpntId = leadEff.getTmplCmpntId();
		this.regnRefCde = leadEff.getRegnRefCde();
		this.suplrId = leadEff.getSuplrId();
		this.seqNbr = leadEff.getSeqNbr();
	}
	
	public EffDteKey(PersTmplCmpntLead lead) {
		this.tmplCmpntId = lead.getTmplCmpntId();
		this.regnRefCde = lead.getRegnRefCde();
		this.suplrId = lead.getSuplrId();
		this.seqNbr = lead.getSeqNbr();
	}
	
	@Override
	public int hashCode() {
		int result=17;
		result = 31*result+tmplCmpntId.hashCode();
		result = 31*result+regnRefCde.hashCode();
		result = 31*result+suplrId.hashCode();
		result = 31*result+seqNbr.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof EffDteKey))
			return false;
		
		EffDteKey toCompare = (EffDteKey)obj;
		
		return Objects.equals(this.tmplCmpntId, toCompare.tmplCmpntId)
				&&Objects.equals(this.regnRefCde, toCompare.regnRefCde)
				&&Objects.equals(this.suplrId, toCompare.suplrId)
				&&Objects.equals(this.seqNbr, toCompare.seqNbr);
	}
}