package com.css.misc.personalization.admin.dao.shpd;

import org.springframework.stereotype.Repository;

import com.css.misc.personalization.admin.dao.CssJpaRepository;
import com.css.misc.personalization.admin.dao.common.PersInvntDiscDaoCommon;
import com.css.misc.personalization.admin.entity.common.PersInvntDisc;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;

@Repository(value="PersInvntDiscDaoShpd")
public interface PersInvntDiscDao extends CssJpaRepository<PersInvntDisc,PersInvntDiscPK>,PersInvntDiscDaoCommon{
	
}
