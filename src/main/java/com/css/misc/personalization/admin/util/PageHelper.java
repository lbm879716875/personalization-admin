package com.css.misc.personalization.admin.util;

import java.security.InvalidParameterException;
import java.util.List;

import com.css.misc.personalization.admin.exception.PageException;

public class PageHelper {
	public static <T> List<T> page(List<T> sortedList,Integer pageNo ,Integer pageSize) throws PageException{
		if(pageNo==null&&pageSize==null)
			return sortedList;
		
		if(pageSize<1||pageNo<0)
			throw new InvalidParameterException("page size and number should be positive");
	
		Integer startIndex = (pageNo)*pageSize;
		Integer endIndex = (pageNo+1)*pageSize;
		Integer listSize = sortedList.size();
		if(startIndex>listSize-1)
			throw new InvalidParameterException("page no is over");
		
		if(endIndex>listSize)
			endIndex = listSize;
		
		return sortedList.subList(startIndex, endIndex);
	}
}
