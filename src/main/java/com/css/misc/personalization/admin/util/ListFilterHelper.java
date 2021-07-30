package com.css.misc.personalization.admin.util;

import java.util.List;
import java.util.stream.Collectors;

import com.css.misc.personalization.admin.model.StatefulEntity;

public class ListFilterHelper {
	public static <T extends StatefulEntity> List<T> statCdeFiltering(List<T> list) {
		return list.stream().filter(p->"A".equals(p.getStatCde())).collect(Collectors.toList());	
	}
}
