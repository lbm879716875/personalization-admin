package com.css.misc.personalization.admin.dao.pers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersAdminRole;

public interface RoleDao extends JpaRepository<PersAdminRole,Integer>{
	
}
