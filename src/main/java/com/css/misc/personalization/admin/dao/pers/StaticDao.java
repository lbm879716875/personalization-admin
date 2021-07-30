package com.css.misc.personalization.admin.dao.pers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class StaticDao{
	
	@PersistenceContext 
	EntityManager entityManager;
	
	public List<String> getSuplrIdList(){
		return entityManager.createNativeQuery("SELECT DISTINCT SUPLR_ID FROM PERS_TMPL_CMPNT_LEAD ORDER BY SUPLR_ID ASC").getResultList();
	}
	
	public List<String> getTmplCmpntIdList(){
		return entityManager.createNativeQuery("SELECT DISTINCT TMPL_CMPNT_ID FROM PERS_TMPL_CMPNT ORDER BY TMPL_CMPNT_ID ASC").getResultList();
	}
	public List<String> getTmplIdList(){
		return entityManager.createNativeQuery("SELECT DISTINCT TMPL_ID FROM PERS_TMPL ORDER BY TMPL_ID ASC").getResultList();
	}
}
