package com.css.misc.personalization.admin.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.css.misc.personalization.admin.entity.common.PersInvntDisc;

public interface PersInvntDiscDaoCommon {
	List<PersInvntDisc> findByDiscTag(String discTag);
	@Transactional
	@Modifying
	List<PersInvntDisc> deleteByDiscTag(String discTag);
}
