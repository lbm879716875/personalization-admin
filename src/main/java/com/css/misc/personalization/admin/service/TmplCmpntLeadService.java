package com.css.misc.personalization.admin.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.css.misc.personalization.admin.constant.Region;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.dao.pers.TmplCmpntLeadDao;
import com.css.misc.personalization.admin.dao.pers.TmplCmpntLeadEffDao;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLead;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.pk.LeadTimePK;
import com.css.misc.personalization.admin.util.AuditLogger;
import com.css.misc.personalization.admin.util.ListFilterHelper;
import com.css.misc.personalization.admin.util.PageHelper;
import com.css.misc.personalization.admin.util.SortHelper;


@Service
public class TmplCmpntLeadService {
	@Autowired TmplCmpntLeadEffDao leadEffDao;
	@Autowired TmplCmpntLeadDao leadDao;
	
	public PersTmplCmpntLeadEffDte get(Integer id) throws NotFoundException {
		return leadEffDao.findById(id).orElseThrow(NotFoundException::new);
	}

	
	public PersTmplCmpntLeadEffDte createLeadTimeEffective(PersTmplCmpntLeadEffDte obj){
		Integer id = obj.getTmplCmpntEffId();
		if(id==null||id==0)
			return leadEffDao.saveWithAuditLog(obj);
		else throw new InvalidParameterException("create function must be without id");
	}
	
	public PersTmplCmpntLeadEffDte updateLeadTimeEffective(PersTmplCmpntLeadEffDte obj) {
		Integer id = obj.getTmplCmpntEffId();
		if(id==null||id==0)
			throw new InvalidParameterException("update function must be with id");
		else
			return leadEffDao.saveWithAuditLog(obj);
	}
	
	
	@Transactional
	public void deleteLeadTimeEffective(Integer... ids) {
		for(Integer id:ids) {
			PersTmplCmpntLeadEffDte ptcled = leadEffDao.findById(id).orElseThrow();
			ptcled.setStatCde("D");
			leadEffDao.saveWithAuditLog(ptcled);
		}
	}


	
	public List<PersTmplCmpntLeadEffDte> regionFilter(List<PersTmplCmpntLeadEffDte> list,Region region){
		if(region!=null)
			return list.stream().filter(p->region.regionCode.equals(p.getRegnRefCde())).collect(Collectors.toList());
		return list;
	}
	


	public PagedObjectList getLeadTimeList(String tmplCmpntId, String suplrId, Region region, String[] sortBy,
			SortDirection[] sortDirection, Integer pageNo, Integer pageSize) throws NotFoundException {
		List<PersTmplCmpntLead> list = null;
		PagedObjectList res = new PagedObjectList();
		list = leadDao.findAll();
		if(list!=null&&list.size()>0) {
			list = ListFilterHelper.statCdeFilter(list);
			list = ListFilterHelper.tmplCmpntIdFilter(list, tmplCmpntId);
			list = ListFilterHelper.suplrIdFilter(list, suplrId);
			list = ListFilterHelper.regnCdeFilter(list,region);
			res.setTotalCount(list.size());
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
			res.setList(list);
		}else
			throw new NotFoundException();
		return res;
	}

	public PersTmplCmpntLead createLeadTime(PersTmplCmpntLead lead) {
		return leadDao.saveWithAuditLog(lead);
	}

	public PersTmplCmpntLead updateLeadTime(PersTmplCmpntLead lead) {
		return leadDao.saveWithAuditLog(lead);
	}
	
	public void deleteLeadTime(LeadTimePK leadPK) {
		PersTmplCmpntLead lead =  leadDao.findById(leadPK).orElseThrow();
		lead.setStatCde("D");
		leadDao.saveWithAuditLog(lead);
	}

	public PagedObjectList getLeadTimeEffectiveList(String tmplCmpntId, Region region, String suplrId, Integer seqNbr,
			String[] sortBy, SortDirection[] sortDirection, Integer pageNo, Integer pageSize) {
		List<PersTmplCmpntLeadEffDte> list = null;
		PagedObjectList res = new PagedObjectList();
		list = leadEffDao.findByTmplCmpntIdAndRegnRefCdeAndSuplrIdAndSeqNbr(tmplCmpntId, region.regionCode, suplrId, seqNbr);
		if(list!=null&&list.size()>0) {
			res.setTotalCount(list.size());
			if(sortBy==null&&sortDirection==null) {
				sortBy=new String[] {"effDte"};
				sortDirection = new SortDirection[] {SortDirection.DESC};
			}
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
		}else {
			res.setTotalCount(0);
		}
		res.setList(list);
		return res;
	}
	

	
	

}
