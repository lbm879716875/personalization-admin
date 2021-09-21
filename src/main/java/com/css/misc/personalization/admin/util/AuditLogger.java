package com.css.misc.personalization.admin.util;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import com.css.misc.personalization.admin.dao.CssJpaRepository;
import com.css.misc.personalization.admin.dao.pers.ActionNameDao;
import com.css.misc.personalization.admin.dao.pers.AuditLogDao;
import com.css.misc.personalization.admin.entity.pers.PersAdminActionName;
import com.css.misc.personalization.admin.entity.pers.PersAdminAuditLog;
import com.css.misc.personalization.admin.model.BaseEntity;
import com.css.misc.personalization.admin.model.pk.ActionNamePK;
import com.google.gson.Gson;

@Component("AuditLogger")
public class AuditLogger implements ApplicationContextAware {
	private static ApplicationContext ac;
	@Autowired HttpServletRequest request;
	@Autowired UserCache userCache;
	@Autowired @Qualifier("persEntityManagerFactory") EntityManager persEm;
	@Autowired @Qualifier("shpdEntityManagerFactory") EntityManager shpdEm;
	@Autowired @Qualifier("alphaEntityManagerFactory") EntityManager alphaEm;
	@Autowired ActionNameDao actionNameDao;
	@Autowired AuditLogDao auditLogDao;
	public static AuditLogger getAuditLogger() {
	    return  ((AuditLogger) ac.getBean("AuditLogger"));
	}
	
	
	public PersAdminAuditLog genLog(BaseEntity entity,CssJpaRepository repo) {
		try {
			entity.setLastModDte(new Date());
			String username=(String) request.getAttribute(UserCache.usernameHeadername);
			entity.setLastModEmplNbr(userCache.get(username).getEmplNbr());
			Gson gson = new Gson();
			String afterJson = gson.toJson(entity);
			String actionId = (String) request.getAttribute(UserCache.actionId);
			String className = entity.getClass().getName();
			String api = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
			String method = request.getMethod();
			PersAdminActionName paan = actionNameDao.findById(new ActionNamePK(api,method)).orElse(null);
			String actionName="";
			if(paan!=null)
				actionName = paan.getActionName();
			Object pk = getPrimaryKey(entity);
			String beforeJson="";
			if(pk!=null) {
				persEm.clear();
				shpdEm.clear();
				alphaEm.clear();
				BaseEntity old = (BaseEntity) repo.findById(pk).orElse(null);
				if(old!=null)
					beforeJson = gson.toJson(old);
			}
			PersAdminAuditLog auditLog = PersAdminAuditLog.builder()
					.withUsername(username)
					.withDateTime(entity.getLastModDte())
					.withActionId(actionId)
					.withActionName(actionName)
					.withApi(api)
					.withHttpMethod(method)
					.withBeforeJson(beforeJson)
					.withAfterJson(afterJson)
					.withClassName(className)
					.build();
			return auditLog;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveLog(PersAdminAuditLog log) {
		if(log!=null) {
			auditLogDao.saveAndFlush(log);
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ac) {
		AuditLogger.ac = ac;
	}
	
	public Object getPrimaryKey(Object obj) throws Exception {
		try {
			persEm.contains(obj);
			return persEm.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(obj);
		}catch(Exception e) {	
		}
		try {
			alphaEm.contains(obj);
			return alphaEm.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(obj);
		}catch(Exception e) {	
		}
		try {
			shpdEm.contains(obj);
			return shpdEm.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(obj);
		}catch(Exception e) {	
		}

		throw new Exception();
	}

}
