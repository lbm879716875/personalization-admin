package com.css.misc.personalization.admin.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;


import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.constant.Tb;
import com.css.misc.personalization.admin.dao.common.PersDiscDescDaoCommon;
import com.css.misc.personalization.admin.dao.common.PersInvntDiscDaoCommon;
import com.css.misc.personalization.admin.entity.common.PersDiscDesc;
import com.css.misc.personalization.admin.entity.common.PersInvntDisc;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;
import com.css.misc.personalization.admin.util.ListFilterHelper;
import com.css.misc.personalization.admin.util.PageHelper;
import com.css.misc.personalization.admin.util.RepoMapper;
import com.css.misc.personalization.admin.util.SortHelper;

@Service
public class PromotionService {
	@Autowired RepoMapper repoMapper;

	public PersDiscDesc getDiscDesc(Db db,String discTag) {
		return (PersDiscDesc)repoMapper.get(db, Tb.PERS_DISC_DESC).findById(discTag).orElseThrow();
	}

	public PagedObjectList getDiscDescList(Db db,String[] sortBy, SortDirection[] sortDirection, Integer pageNo,
			Integer pageSize) throws NotFoundException {
		PagedObjectList res = new PagedObjectList();
		List<PersDiscDesc> list = repoMapper.get(db, Tb.PERS_DISC_DESC).findAll();
		if(list!=null&&list.size()>0) {
			list = ListFilterHelper.statCdeFiltering(list);
			res.setTotalCount(list.size());
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
			res.setList(list);
		}else
			throw new NotFoundException();
		return res;
	}

	public PersDiscDesc createDiscDesc(Db db,PersDiscDesc persDiscDesc) {
		persDiscDesc.setLastModDte(new Date());
		return (PersDiscDesc) repoMapper.get(db, Tb.PERS_DISC_DESC).saveAndFlush(persDiscDesc);
	}

	public PersDiscDesc updateDiscDesc(Db db,PersDiscDesc persDiscDesc) {
		persDiscDesc.setLastModDte(new Date());
		return (PersDiscDesc) repoMapper.get(db, Tb.PERS_DISC_DESC).saveAndFlush(persDiscDesc);
	}
	
	@Transactional
	public void deleteDiscDesc(Db db, String... discTags) {
		((PersDiscDescDaoCommon)repoMapper.get(db, Tb.PERS_DISC_DESC)).deleteByDiscTags(discTags);
		
	}
	
	public PagedObjectList getInvntDiscList(Db db,String discTag,String[] sortBy, SortDirection[] sortDirection, 
			Integer pageNo,Integer pageSize) {
		PagedObjectList res = new PagedObjectList();
		PersInvntDiscDaoCommon repo = (PersInvntDiscDaoCommon)repoMapper.get(db, Tb.PERS_INVNT_DISC);
		List<PersInvntDisc> list = repo.findByDiscTag(discTag);
		if(list!=null&&list.size()>0) {
			res.setTotalCount(list.size());
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
			res.setList(list);
		}else
			res.setTotalCount(0);
		return res;
	}
	
	public void deleteInvntDisc(Db db, String discTag) {
		PersInvntDiscDaoCommon repo = (PersInvntDiscDaoCommon)repoMapper.get(db, Tb.PERS_INVNT_DISC);
		repo.deleteByDiscTag(discTag);
	}
	public void deleteInvntDisc(Db db, PersInvntDiscPK pk) {
		repoMapper.get(db, Tb.PERS_INVNT_DISC).deleteById(pk);
	}
	
	public PersInvntDisc updateInvntDisc(Db db,PersInvntDisc invntDisc) {
		invntDisc.setLastModDte(new Date());
		return (PersInvntDisc)repoMapper.get(db, Tb.PERS_INVNT_DISC).saveAndFlush(invntDisc);
	}
	public void createInvntDisc(Db db, List<PersInvntDisc> list) {
		repoMapper.get(db, Tb.PERS_INVNT_DISC).saveAll(list);	
	}
	
	
	
}
