package com.css.misc.personalization.admin.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.Tb;

@Component
public class RepoMapper {
	private static final Log log = LogFactory.getLog(RepoMapper.class);
	Map<Db,Map<Tb,JpaRepository>> repoMap = new HashMap<>();
	Map<Tb,JpaRepository> shpdRepoMap = new HashMap<>();
	Map<Tb,JpaRepository> alphaRepoMap = new HashMap<>();
	
	@Autowired List<JpaRepository> repoList;
	@PostConstruct
	private void init() {
		for(JpaRepository repo:repoList) {
			Db db;
			Tb tb;
			
			Class clazz = repo.getClass().getInterfaces()[0];
			if(clazz.getPackageName().endsWith(".shpd"))
				db = Db.SHPD;
			else if(clazz.getPackageName().endsWith(".alpha"))
				db = Db.ALPHA;
			else
				//only alpha&shpd have same table
				continue;
			
			Type[] interfaceTypes = clazz.getGenericInterfaces();
			Class<?> entityClass=null;
			for(Type type:interfaceTypes) {
				if(type.getTypeName().contains("JpaRepository")) {
					ParameterizedType pt = (ParameterizedType)type;
					entityClass = (Class<?>) pt.getActualTypeArguments()[0];
					break;
				}		
			}
			if(entityClass!=null) {
				Annotation[] annotations = entityClass.getAnnotations();
				for(Annotation annotation:annotations) {
					if(annotation instanceof Table) {
						String tbName = ((Table)annotation).name();
						try {
							tb = Tb.valueOf(tbName);
							if(db.equals(Db.SHPD)) 
								shpdRepoMap.put(tb, repo);
							else if(db.equals(Db.ALPHA))
								alphaRepoMap.put(tb, repo);
							break;
						}catch(Exception e) {
							log.debug(e);
							break;
						}
						
					}
				}
			}		
		}
		repoMap.put(Db.ALPHA, alphaRepoMap);
		repoMap.put(Db.SHPD, shpdRepoMap);
	}
	
	public JpaRepository get(Db db,Tb tb) {
		return repoMap.get(db).get(tb);
	}
}
