package com.css.misc.personalization.admin.entity.pers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.css.misc.personalization.admin.model.StatefulEntity;
import com.css.misc.personalization.admin.model.pk.EffDteKey;

@Entity
@Table(name="PERS_TMPL_CMPNT_LEAD")
@IdClass(EffDteKey.class)
public class PersTmplCmpntLead extends StatefulEntity{
	@Id
	@Column(name="TMPL_CMPNT_ID")
	private String tmplCmpntId;
	@Id
	@Column(name="REGN_REF_CDE")
	private String regnRefCde;
	@Id
	@Column(name="SUPLR_ID")
	private String suplrId;
	@Id
	@Column(name="SEQ_NBR")
	private Integer seqNbr;
	@Column(name="PROC_NAM")
	private String procNam;
	@Column(name="PROC_REGN_REF_CDE")
	private String procRegnRefCde;
	@Column(name="PROC_TYPE")
	private String procType;
	@Column(name="LEAD_TIME_HV_STOCK")
	private String leadTimeHvStock;
	@Column(name="LEAD_TIME_WOUT_STOCK")
	private String leadTimeWoutStock;
	@Column(name="RULE_JSON_CTENT")
	private String ruleJsonCtent;
	@Column(name="PRI")
	private String pri;
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
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getProcNam() {
		return procNam;
	}
	public void setProcNam(String procNam) {
		this.procNam = procNam;
	}
	public String getProcRegnRefCde() {
		return procRegnRefCde;
	}
	public void setProcRegnRefCde(String procRegnRefCde) {
		this.procRegnRefCde = procRegnRefCde;
	}
	public String getProcType() {
		return procType;
	}
	public void setProcType(String procType) {
		this.procType = procType;
	}
	public String getLeadTimeHvStock() {
		return leadTimeHvStock;
	}
	public void setLeadTimeHvStock(String leadTimeHvStock) {
		this.leadTimeHvStock = leadTimeHvStock;
	}
	public String getLeadTimeWoutStock() {
		return leadTimeWoutStock;
	}
	public void setLeadTimeWoutStock(String leadTimeWoutStock) {
		this.leadTimeWoutStock = leadTimeWoutStock;
	}
	public String getRuleJsonCtent() {
		return ruleJsonCtent;
	}
	public void setRuleJsonCtent(String ruleJsonCtent) {
		this.ruleJsonCtent = ruleJsonCtent;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	
}
