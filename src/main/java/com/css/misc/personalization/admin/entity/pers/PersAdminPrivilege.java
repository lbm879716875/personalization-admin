package com.css.misc.personalization.admin.entity.pers;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PERS_ADMIN_PRIVILEGE")
public class PersAdminPrivilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRIVILEGE_ID")
	private Integer privilegeId;
	@Column(name="PATTERN")
	private String pattern;
	@Column(name="HTTP_METHOD")
	private String httpMethod;
	@Column(name="SUPLR_ID")
	private String suplrId;
	@ManyToMany(mappedBy="privilegeList",fetch = FetchType.EAGER)
	List<PersAdminRole> roleList;
	
	public Integer getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getSuplrId() {
		return suplrId;
	}
	public void setSuplrId(String suplrId) {
		this.suplrId = suplrId;
	}
	public List<PersAdminRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<PersAdminRole> roleList) {
		this.roleList = roleList;
	}
	
	
	
}
