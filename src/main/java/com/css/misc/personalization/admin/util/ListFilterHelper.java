package com.css.misc.personalization.admin.util;

import java.util.List;
import java.util.stream.Collectors;

import com.css.misc.personalization.admin.constant.Region;
import com.css.misc.personalization.admin.entity.interfaces.WithDb;
import com.css.misc.personalization.admin.entity.interfaces.WithRegnCde;
import com.css.misc.personalization.admin.entity.interfaces.WithSuplrId;
import com.css.misc.personalization.admin.entity.interfaces.WithTmplCmpntId;
import com.css.misc.personalization.admin.model.StatefulEntity;

public class ListFilterHelper {
	public static <T extends StatefulEntity> List<T> statCdeFilter(List<T> list) {
		return list.stream().filter(p->"A".equals(p.getStatCde())).collect(Collectors.toList());	
	}
	
	public static <T extends WithDb> List<T> dbFilter(List<T> list,Region region) {
		if(region!=null)
			return list.stream().filter(p->region.db.equals(p.getDb())).collect(Collectors.toList());
		return list;
	}
	
	public static <T extends WithRegnCde> List<T> regnCdeFilter(List<T> list,Region region) {
		if(region!=null)
			return list.stream().filter(p->region.regionCode.equals(p.getRegnRefCde())).collect(Collectors.toList());
		return list;
	}
	
	public static <T extends WithTmplCmpntId> List<T> tmplCmpntIdFilter(List<T> list,String tmplCmpntId) {
		if(tmplCmpntId!=null)
			return list.stream().filter(p->tmplCmpntId.equals(p.getTmplCmpntId())).collect(Collectors.toList());
		return list;
	}
	
	public static <T extends WithSuplrId> List<T> suplrIdFilter(List<T> list,String suplrId) {
		if(suplrId!=null)
			return list.stream().filter(p->suplrId.equals(p.getSuplrId())).collect(Collectors.toList());
		return list;
	}
}
