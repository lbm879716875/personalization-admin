package com.css.misc.personalization.admin.entity.pers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table(name="PERS_ADMIN_AUDIT_LOG")
public class PersAdminAuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LOG_ID")
	private Integer logId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="DATETIME")
	private Date dateTime;
	
	@Column(name="ACTION_ID")
	private String actionId;
	
	@Column(name="ACTION_NAME")
	private String actionName;
	
	@Column(name="API")
	private String api;
	
	@Column(name="HTTP_METHOD")
	private String httpMethod;
	
	@Column(name="BEFORE_JSON")
	private String beforeJson;
	
	@Column(name="AFTER_JSON")
	private String afterJson;
	
	@Column(name="CLASS_NAME")
	private String className;


	@Generated("SparkTools") private PersAdminAuditLog(Builder builder){this.logId=builder.logId;this.username=builder.username;this.dateTime=builder.dateTime;this.actionId=builder.actionId;this.actionName=builder.actionName;this.api=builder.api;this.httpMethod=builder.httpMethod;this.beforeJson=builder.beforeJson;this.afterJson=builder.afterJson;this.className=builder.className;}


	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

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

	public String getBeforeJson() {
		return beforeJson;
	}

	public void setBeforeJson(String beforeJson) {
		this.beforeJson = beforeJson;
	}

	public String getAfterJson() {
		return afterJson;
	}

	public void setAfterJson(String afterJson) {
		this.afterJson = afterJson;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Creates builder to build {@link PersAdminAuditLog}.
	 * @return created builder
	 */@Generated("SparkTools") public static Builder builder(){return new Builder();}

	/**
	 * Builder to build {@link PersAdminAuditLog}.
	 */@Generated("SparkTools") public static final class Builder {private Integer logId;private String username;private Date dateTime;private String actionId;private String actionName;private String api;private String httpMethod;private String beforeJson;private String afterJson;private String className;private Builder(){}public Builder withLogId(Integer logId){this.logId=logId;return this;}public Builder withUsername(String username){this.username=username;return this;}public Builder withDateTime(Date dateTime){this.dateTime=dateTime;return this;}public Builder withActionId(String actionId){this.actionId=actionId;return this;}public Builder withActionName(String actionName){this.actionName=actionName;return this;}public Builder withApi(String api){this.api=api;return this;}public Builder withHttpMethod(String httpMethod){this.httpMethod=httpMethod;return this;}public Builder withBeforeJson(String beforeJson){this.beforeJson=beforeJson;return this;}public Builder withAfterJson(String afterJson){this.afterJson=afterJson;return this;}public Builder withClassName(String className){this.className=className;return this;}public PersAdminAuditLog build(){return new PersAdminAuditLog(this);}}

	
	
}
