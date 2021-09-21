package com.css.misc.personalization.admin.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.Region;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.constant.Tb;
import com.css.misc.personalization.admin.dao.CssJpaRepository;
import com.css.misc.personalization.admin.dao.common.PersDiscDescDaoCommon;
import com.css.misc.personalization.admin.dao.common.PersInvntDiscDaoCommon;
import com.css.misc.personalization.admin.entity.common.PersDiscDesc;
import com.css.misc.personalization.admin.entity.common.PersInvntDisc;
import com.css.misc.personalization.admin.entity.pers.PersAdminAuditLog;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;
import com.css.misc.personalization.admin.util.AuditLogger;
import com.css.misc.personalization.admin.util.ListFilterHelper;
import com.css.misc.personalization.admin.util.PageHelper;
import com.css.misc.personalization.admin.util.RepoMapper;
import com.css.misc.personalization.admin.util.SortHelper;

@Service
public class PromotionService {
	@Autowired RepoMapper repoMapper;
	@Autowired AuditLogger auditLogger;
	public PersDiscDesc getDiscDesc(Db db,String discTag) {
		return (PersDiscDesc)repoMapper.get(db, Tb.PERS_DISC_DESC).findById(discTag).orElseThrow();
	}

	public PagedObjectList getDiscDescList(Region region,String[] sortBy, SortDirection[] sortDirection, Integer pageNo,
			Integer pageSize) throws NotFoundException {
		PagedObjectList res = new PagedObjectList();
		try {
			List<PersDiscDesc> list = repoMapper.get(Db.ALPHA, Tb.PERS_DISC_DESC).findAll();
			list.forEach(a->a.setDb(Db.ALPHA));
			List<PersDiscDesc> shpdList = repoMapper.get(Db.SHPD, Tb.PERS_DISC_DESC).findAll();
			shpdList.forEach(a->a.setDb(Db.SHPD));
			list.addAll(shpdList);
			
			list = ListFilterHelper.statCdeFilter(list);
			list = ListFilterHelper.dbFilter(list,region);
			res.setTotalCount(list.size());
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
			list = patchData(list);
			res.setList(list);
		}
		catch(Exception e) {
			throw new NotFoundException();
		}
		return res;
	}

	public List<PersDiscDesc> patchData(List<PersDiscDesc> list){
		for(PersDiscDesc p:list) {
			Integer invntCount = ((PersInvntDiscDaoCommon)repoMapper.get(p.getDb(), Tb.PERS_INVNT_DISC)).countByDiscTag(p.getDiscTag());
			p.setInvntCount(invntCount);
		}
		return list;
	}
	
	public PersDiscDesc createDiscDesc(Db db,PersDiscDesc persDiscDesc) {
		return (PersDiscDesc) repoMapper.get(db, Tb.PERS_DISC_DESC).saveWithAuditLog(persDiscDesc);
	}

	public PersDiscDesc updateDiscDesc(Db db,PersDiscDesc persDiscDesc) {
		return (PersDiscDesc) repoMapper.get(db, Tb.PERS_DISC_DESC).saveWithAuditLog(persDiscDesc);
	}
	
	@Transactional(value=TxType.SUPPORTS)
	public void deleteDiscDesc(Db db, String... discTags) {
		CssJpaRepository repo = repoMapper.get(db, Tb.PERS_DISC_DESC);
		for(String discTag: discTags) {
			PersDiscDesc pdd = (PersDiscDesc) repo.findById(discTag).orElseThrow();
			pdd.setStatCde("D");
			repo.saveWithAuditLog(pdd);
		}
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
	//transaction logic not finished, to do
	@Transactional(value=TxType.SUPPORTS)
	public void deleteInvntDisc(Db db, String discTag) {
		PersInvntDiscDaoCommon repo = (PersInvntDiscDaoCommon)repoMapper.get(db, Tb.PERS_INVNT_DISC);
		List<PersInvntDisc> list = repo.findByDiscTag(discTag);
		for(PersInvntDisc pid:list) {
			deleteInvntDisc(db,new PersInvntDiscPK(pid));
		}
	}
	
	@Transactional(value=TxType.SUPPORTS)
	public void deleteInvntDisc(Db db, PersInvntDiscPK pk) {
		CssJpaRepository repo = repoMapper.get(db, Tb.PERS_INVNT_DISC);
		PersAdminAuditLog log = auditLogger.genLog((PersInvntDisc)repo.findById(pk).orElseThrow(), repo);
		repoMapper.get(db, Tb.PERS_INVNT_DISC).deleteById(pk);
		log.setAfterJson(null);
		auditLogger.saveLog(log);
	}
	
	public PersInvntDisc updateInvntDisc(Db db,PersInvntDisc invntDisc) {
		return (PersInvntDisc)repoMapper.get(db, Tb.PERS_INVNT_DISC).saveWithAuditLog(invntDisc);
	}
	
	@Transactional(value=TxType.SUPPORTS)
	public void createInvntDisc(Db db, List<PersInvntDisc> list) {
		CssJpaRepository repo = repoMapper.get(db, Tb.PERS_INVNT_DISC);
		for(PersInvntDisc pid:list) {
			repo.saveWithAuditLog(pid);
		}
	}

}
