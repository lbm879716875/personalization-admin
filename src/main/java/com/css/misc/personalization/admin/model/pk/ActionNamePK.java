package com.css.misc.personalization.admin.model.pk;

import java.io.Serializable;

public class ActionNamePK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7272785145399726686L;
	private String api;
	private String httpMethod;	
	public ActionNamePK() {
		super();
	}
	
	public ActionNamePK(String api, String httpMethod) {
		super();
		this.api = api;
		this.httpMethod = httpMethod;
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
	
}
