package com.css.misc.personalization.admin.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.css.misc.personalization.admin.constant.Accessor;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.exception.SortException;

public class SortHelper {
	public static <T> List<T> sort(List<T> list,String[] sortBy ,SortDirection[] sortDirection) throws SortException{
		
		if(sortBy==null||sortDirection==null)
			return list;	
		
		if(sortBy.length!=sortDirection.length)
			throw new InvalidParameterException("number of sort param mismatch");
		try {
			if(list!=null&&list.size()>0) {
				Class clazz = list.get(0).getClass();
				Comparator comparator = null;
				for(int i=0;i<sortBy.length;i++) {
					String getter = StringUtil.accessorName(sortBy[i], Accessor.get);
					Method method = clazz.getMethod(getter);
					if(!Comparable.class.isAssignableFrom(method.getReturnType())&&!method.getReturnType().isPrimitive()) {
						throw new InvalidParameterException("sortBy column is not a comparable class");
					}
				
					if(comparator==null)
						comparator = new CommonComparator(method,sortDirection[i]);
					else 
						comparator = comparator.thenComparing(new CommonComparator(method,sortDirection[i]));
				}
				Collections.sort(list,comparator);
			}
		}catch(NoSuchMethodException | SecurityException e){
			e.printStackTrace();
			throw new InvalidParameterException("sortBy column name error");
		}
		return list;
	}
	
	static class CommonComparator implements Comparator{
		Method getter;
		SortDirection sortDirection;
		CommonComparator(Method getter,SortDirection sortDirection){
			this.getter=getter;
			this.sortDirection = sortDirection;
		}
		@Override
		public int compare(Object o1, Object o2) {
			try {
				Comparable toCompare1 = (Comparable)getter.invoke(o1);
				Comparable toCompare2 = (Comparable)getter.invoke(o2);
				if(SortDirection.ASC==sortDirection)
					return toCompare1.compareTo(toCompare2);
				else 
					return toCompare2.compareTo(toCompare1);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new InvalidParameterException("sortBy column is not accessable");
			}
		}
	}
}
