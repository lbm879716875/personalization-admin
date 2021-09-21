package com.css.misc.personalization.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersAdminAuditLog;
import com.css.misc.personalization.admin.model.BaseEntity;
import com.css.misc.personalization.admin.util.AuditLogger;

public interface CssJpaRepository<T extends BaseEntity, ID> extends  JpaRepository<T, ID>{
	default <S extends T> S saveWithAuditLog(S entity) {
		AuditLogger al = AuditLogger.getAuditLogger();
		PersAdminAuditLog log = al.genLog(entity, this);
		S e =  saveAndFlush(entity);
		al.saveLog(log);
		return e;
	}

}
