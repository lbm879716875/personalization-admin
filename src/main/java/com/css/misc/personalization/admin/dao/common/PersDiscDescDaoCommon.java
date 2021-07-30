package com.css.misc.personalization.admin.dao.common;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PersDiscDescDaoCommon{
	@Transactional
	@Modifying
	@Query("UPDATE PersDiscDesc SET statCde='D' WHERE discTag in :discTags")
	public void deleteByDiscTags(@Param(value = "discTags") String... discTags);
}
