package com.css.misc.personalization.admin.model;

import java.util.List;

public class PagedObjectList {
	Integer totalCount;
	List<?> list;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
