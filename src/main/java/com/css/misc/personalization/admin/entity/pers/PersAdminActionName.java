package com.css.misc.personalization.admin.entity.pers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.css.misc.personalization.admin.model.pk.ActionNamePK;

@Entity
@Table(name="PERS_ADMIN_ACTION_NAME")
@IdClass(ActionNamePK.class)
public class PersAdminActionName {
	@Id
	@Column(name="API")
	private String api;
	@Id
	@Column(name="HTTP_METHOD")
	private String httpMethod;
	@Column(name="ACTION_NAME")
	private String actionName;
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
}
