package com.css.misc.personalization.admin.dao.pers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;

@Repository
public interface LeadTimeDao extends JpaRepository<PersTmplCmpntLeadEffDte,Integer>{
	public List<PersTmplCmpntLeadEffDte> findBySuplrId(String suplrId);
	public List<PersTmplCmpntLeadEffDte> findByTmplCmpntId(String tmplCmpntId);
	public List<PersTmplCmpntLeadEffDte> findBySuplrIdAndTmplCmpntId(String suplrId,String tmplCmpntId);
	
	@Modifying
	@Query("UPDATE PersTmplCmpntLeadEffDte SET statCde='D' WHERE id in :idList")
	public void deleteById(@Param(value = "idList") Integer... idList);
	
}
