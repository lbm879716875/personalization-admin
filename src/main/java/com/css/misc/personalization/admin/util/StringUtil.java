package com.css.misc.personalization.admin.util;

import com.css.misc.personalization.admin.constant.Accessor;

public class StringUtil {
	public static String accessorName(String fieldName,Accessor accessor) {
		return accessor + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
