package com.css.misc.personalization.admin.entity.pers;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.css.misc.personalization.admin.model.StatefulEntity;

@Entity
@Table(name="PERS_ADMIN_USER")
public class PersAdminUser extends StatefulEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="EMPL_NBR")
	private String emplNbr;
	
	@ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinTable(
			name = "PERS_ADMIN_USER_ROLE",
			joinColumns=@JoinColumn(name = "USER_ID") , 
			inverseJoinColumns =  @JoinColumn(name = "ROLE_ID") 
			)
	List<PersAdminRole> roleList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmplNbr() {
		return emplNbr;
	}

	public void setEmplNbr(String emplNbr) {
		this.emplNbr = emplNbr;
	}

	public List<PersAdminRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<PersAdminRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersAdminUser other = (PersAdminUser) obj;
		if (emplNbr == null) {
			if (other.emplNbr != null)
				return false;
		} else if (!emplNbr.equals(other.emplNbr))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
