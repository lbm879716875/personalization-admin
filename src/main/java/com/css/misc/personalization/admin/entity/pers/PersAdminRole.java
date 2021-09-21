package com.css.misc.personalization.admin.entity.pers;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PERS_ADMIN_ROLE")
public class PersAdminRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Integer roleId;
	@Column(name="ROLE_NAME")
	private String roleName;
	@Column(name="DEPARTMENT_ID")
	private String departmentId;
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	@ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinTable(
			name = "PERS_ADMIN_ROLE_PRIVILEGE",
			joinColumns=@JoinColumn(name = "ROLE_ID") , 
			inverseJoinColumns =  @JoinColumn(name = "PRIVILEGE_ID") 
			)
	List<PersAdminPrivilege> privilegeList;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<PersAdminPrivilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<PersAdminPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
	
	
}
