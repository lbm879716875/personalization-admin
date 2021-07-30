package com.css.misc.personalization.admin.dao.pers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLead;
import com.css.misc.personalization.admin.model.pk.EffDteKey;

public interface LeadDao extends JpaRepository<PersTmplCmpntLead,EffDteKey>{
	
}
