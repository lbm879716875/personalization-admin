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

import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.dao.pers.LeadDao;
import com.css.misc.personalization.admin.dao.pers.LeadTimeDao;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLead;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.pk.EffDteKey;
import com.css.misc.personalization.admin.util.ListFilterHelper;
import com.css.misc.personalization.admin.util.PageHelper;
import com.css.misc.personalization.admin.util.SortHelper;


@Service
public class LeadTimeService {
	@Autowired LeadTimeDao leadTimeDao;
	@Autowired LeadDao leadDao;
	
	public PersTmplCmpntLeadEffDte get(Integer id) throws NotFoundException {
		return leadTimeDao.findById(id).orElseThrow(NotFoundException::new);
	}

	public PagedObjectList getList(String suplrId,String tmplCmpntId,String[] sortBy,SortDirection[] sortDirection, Integer pageNo, Integer pageSize,Date timePoint,String regionCode) throws InvalidParameterException, NotFoundException{
		List<PersTmplCmpntLeadEffDte> list = null;
		PagedObjectList res = new PagedObjectList();
		if(suplrId!=null&&tmplCmpntId!=null)
			list = leadTimeDao.findBySuplrIdAndTmplCmpntId(suplrId, tmplCmpntId);
		else if(suplrId!=null)
			list = leadTimeDao.findBySuplrId(suplrId);
		else if(tmplCmpntId!=null)
			list = leadTimeDao.findByTmplCmpntId(tmplCmpntId);
		else if(suplrId==null&&tmplCmpntId==null)
			list = leadTimeDao.findAll();
		if(list!=null&&list.size()>0) {
			list = ListFilterHelper.statCdeFiltering(list);
			list = regionCodeFiltering(list,regionCode);
			list = effDteFiltering(list,timePoint);
			res.setTotalCount(list.size());
			list = SortHelper.sort(list,sortBy,sortDirection);
			list = PageHelper.page(list, pageNo, pageSize);
			list = patchData(list);
			res.setList(list);
		}else
			throw new NotFoundException();
		return res;
	}
	
	public PersTmplCmpntLeadEffDte create(PersTmplCmpntLeadEffDte obj){
		obj.setLastModDte(new Date());
		Integer id = obj.getTmplCmpntEffId();
		if(id==null||id==0)
			return leadTimeDao.saveAndFlush(obj);
		else throw new InvalidParameterException("create function must be without id");
	}
	
	public PersTmplCmpntLeadEffDte update(PersTmplCmpntLeadEffDte obj) {
		Integer id = obj.getTmplCmpntEffId();
		if(id==null||id==0)
			throw new InvalidParameterException("update function must be with id");
		else
			return leadTimeDao.saveAndFlush(obj);
	}
	
	
	@Transactional
	public void delete(Integer... ids) {
		leadTimeDao.deleteById(ids);
	}

	private List<PersTmplCmpntLeadEffDte> effDteFiltering(List<PersTmplCmpntLeadEffDte> list, Date timePoint) {
		if(timePoint==null)
			timePoint = new Date();
		
		Map<EffDteKey,Date> lastestEffDteMap = new HashMap<>();
		
		for(int i=0;i<list.size();i++) {
			PersTmplCmpntLeadEffDte p = list.get(i);
			EffDteKey key = new EffDteKey(p);
			Date effDte = p.getEffDte();
			if(timePoint.compareTo(effDte)>0) {
				if(!lastestEffDteMap.containsKey(key)) {
					lastestEffDteMap.put(key, effDte);
				}else if(lastestEffDteMap.get(key).compareTo(effDte)<0) {
					lastestEffDteMap.put(key, effDte);
				}
			}
		}
		
		Predicate<PersTmplCmpntLeadEffDte> predi = new Predicate<PersTmplCmpntLeadEffDte>() {
			@Override
			public boolean test(PersTmplCmpntLeadEffDte p) {
				EffDteKey key = new EffDteKey(p);
				Date effDte = p.getEffDte();
				if(lastestEffDteMap.containsKey(key)&&lastestEffDteMap.get(key).compareTo(effDte)==0) 
					p.setEffectived(true);
				boolean res = !(lastestEffDteMap.containsKey(key)&&lastestEffDteMap.get(key).compareTo(effDte)>0);
				return res;
			}
		};
		return list.stream().filter(predi).collect(Collectors.toList());
	}
	
	public List<PersTmplCmpntLeadEffDte> regionCodeFiltering(List<PersTmplCmpntLeadEffDte> list,String regionCode){
		if(regionCode!=null&&!"".equals(regionCode))
			return list.stream().filter(p->regionCode.equals(p.getRegnRefCde())).collect(Collectors.toList());
		return list;
	}
	
	public List<PersTmplCmpntLeadEffDte> patchData(List<PersTmplCmpntLeadEffDte> list){
		List<EffDteKey> keyList = new ArrayList<>();
		for(PersTmplCmpntLeadEffDte p:list) {
			EffDteKey key = new EffDteKey(p);
			keyList.add(key);
		}
		List<PersTmplCmpntLead> leadList = leadDao.findAllById(keyList);
		Map<EffDteKey,PersTmplCmpntLead> leadMap = new HashMap<>();
		for(PersTmplCmpntLead lead:leadList) {
			leadMap.put(new EffDteKey(lead), lead);
		}
		for(PersTmplCmpntLeadEffDte p:list) {
			PersTmplCmpntLead lead = leadMap.get(new EffDteKey(p));
			if(lead!=null) {
				p.setRuleJsonCtent(lead.getRuleJsonCtent());
				p.setProcNam(lead.getProcNam());
			}
		}
		return list;
	}
	

	
	

}
