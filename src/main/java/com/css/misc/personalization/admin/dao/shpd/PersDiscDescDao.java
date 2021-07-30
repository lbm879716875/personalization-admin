package com.css.misc.personalization.admin.dao.shpd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.css.misc.personalization.admin.dao.common.PersDiscDescDaoCommon;
import com.css.misc.personalization.admin.entity.common.PersDiscDesc;

@Repository(value="PersDiscDescDaoShpd")
public interface PersDiscDescDao extends JpaRepository<PersDiscDesc,String>,PersDiscDescDaoCommon{
	
}
