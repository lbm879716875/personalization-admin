package com.css.misc.personalization.admin.dao.pers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersAdminActionName;
import com.css.misc.personalization.admin.model.pk.ActionNamePK;

public interface ActionNameDao extends JpaRepository<PersAdminActionName,ActionNamePK>{
	
}
