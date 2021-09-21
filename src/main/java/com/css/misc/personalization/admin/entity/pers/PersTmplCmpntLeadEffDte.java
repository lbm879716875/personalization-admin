package com.css.misc.personalization.admin.entity.pers;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.misc.personalization.admin.model.StatefulEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name="PERS_TMPL_CMPNT_LEAD_EFF_DTE")
public class PersTmplCmpntLeadEffDte extends StatefulEntity{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TMPL_CMPNT_EFF_ID")
	private Integer tmplCmpntEffId;
	@Column(name="TMPL_CMPNT_ID")
	private String tmplCmpntId;
	@Column(name="REGN_REF_CDE")
	private String regnRefCde;
	@Column(name="SUPLR_ID")
	private String suplrId;
	@Column(name="SEQ_NBR")
	private Integer seqNbr;
	@Column(name="EFF_DTE")
	private Date effDte;
	@Column(name="LEAD_TIME_HV_STOCK")
	private BigDecimal leadTimeHvStock;
	@Column(name="LEAD_TIME_WOUT_STOCK")
	private BigDecimal leadTimeWoutStock;

	

	public Integer getTmplCmpntEffId() {
		return tmplCmpntEffId;
	}
	public void setTmplCmpntEffId(Integer tmplCmpntEffId) {
		this.tmplCmpntEffId = tmplCmpntEffId;
	}
	public String getTmplCmpntId() {
		return tmplCmpntId;
	}
	public void setTmplCmpntId(String tmplCmpntId) {
		this.tmplCmpntId = tmplCmpntId;
	}
	public String getRegnRefCde() {
		return regnRefCde;
	}
	public void setRegnRefCde(String regnRefCde) {
		this.regnRefCde = regnRefCde;
	}
	public String getSuplrId() {
		return suplrId;
	}
	public void setSuplrId(String suplrId) {
		this.suplrId = suplrId;
	}
	public int getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public Date getEffDte() {
		return effDte;
	}
	public void setEffDte(Date effDte) {
		this.effDte = effDte;
	}
	public BigDecimal getLeadTimeHvStock() {
		return leadTimeHvStock;
	}
	public void setLeadTimeHvStock(BigDecimal leadTimeHvStock) {
		this.leadTimeHvStock = leadTimeHvStock;
	}
	public BigDecimal getLeadTimeWoutStock() {
		return leadTimeWoutStock;
	}
	public void setLeadTimeWoutStock(BigDecimal leadTimeWoutStock) {
		this.leadTimeWoutStock = leadTimeWoutStock;
	}
	
	
}
