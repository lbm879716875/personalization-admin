package com.css.misc.personalization.admin.dao.alpha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.css.misc.personalization.admin.dao.common.PersInvntDiscDaoCommon;
import com.css.misc.personalization.admin.entity.common.PersInvntDisc;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;

@Repository(value="PersInvntDiscDaoAlpha")
public interface PersInvntDiscDao extends JpaRepository<PersInvntDisc,PersInvntDiscPK>,PersInvntDiscDaoCommon{
	
}
