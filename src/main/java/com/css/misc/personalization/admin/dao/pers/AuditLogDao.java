package com.css.misc.personalization.admin.dao.pers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersAdminAuditLog;

public interface AuditLogDao extends JpaRepository<PersAdminAuditLog,Integer>{
	
}
