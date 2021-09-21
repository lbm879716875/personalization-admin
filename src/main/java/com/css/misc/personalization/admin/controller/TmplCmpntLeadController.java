package com.css.misc.personalization.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.constant.Region;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLead;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.pk.LeadTimePK;
import com.css.misc.personalization.admin.service.TmplCmpntLeadService;

import io.micrometer.core.annotation.Timed;

@RestController
@Timed
@RequestMapping(value = "/template/component")
public class TmplCmpntLeadController {
	@Autowired TmplCmpntLeadService leadService;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<PersTmplCmpntLeadEffDte> handleNotFound(NotFoundException ex) {
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="leadtimes")
	public ResponseEntity<PagedObjectList> getLeadTime(
			String tmplCmpntId,
			String suplrId,
			Region region,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize
			) throws NotFoundException{
		PagedObjectList res = leadService.getLeadTimeList(
				tmplCmpntId,suplrId,region,sortBy,sortDirection,pageNo,pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="leadtime")
	public ResponseEntity<PersTmplCmpntLead> createLead(@RequestBody PersTmplCmpntLead lead){
		return ResponseEntity.status(HttpStatus.CREATED).body(leadService.createLeadTime(lead));
	}
	
	@PutMapping(value="leadtime")
	public ResponseEntity<PersTmplCmpntLead> updateLead(@RequestBody PersTmplCmpntLead lead){
		return ResponseEntity.status(HttpStatus.CREATED).body(leadService.updateLeadTime(lead));
	}
	
	@DeleteMapping(value="leadtime")
	public ResponseEntity<?> delete(@RequestBody LeadTimePK leadPK){
		leadService.deleteLeadTime(leadPK);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@GetMapping(value="leadtime/effectives")
	public ResponseEntity<PagedObjectList> getLeadTimeEffectiveList(
			String tmplCmpntId,	
			Region region,
			String suplrId,
			Integer seqNbr,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize
			) throws NotFoundException{
		PagedObjectList res = leadService.getLeadTimeEffectiveList(
				tmplCmpntId,region,suplrId,seqNbr,sortBy,sortDirection,pageNo,pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="leadtime/effective",consumes = "application/json")
	public ResponseEntity<PersTmplCmpntLeadEffDte> createLeadTimeEffectiveList(@RequestBody PersTmplCmpntLeadEffDte eff) {
		return ResponseEntity.status(HttpStatus.CREATED).body(leadService.createLeadTimeEffective(eff));
	}
	

	@PutMapping(value="leadtime/effective/{id:\\d+}",consumes = "application/json")
	public ResponseEntity<PersTmplCmpntLeadEffDte> updateLeadTimeEffective(@PathVariable("id") Integer id,@RequestBody PersTmplCmpntLeadEffDte obj) {
		obj.setTmplCmpntEffId(id);
		return ResponseEntity.status(HttpStatus.OK).body(leadService.updateLeadTimeEffective(obj));
	}
	
	@DeleteMapping(value="leadtime/effective/{id:\\d+}")
	public ResponseEntity<?> deleteLeadTimeEffective(@PathVariable("id") Integer id) throws NotFoundException{
		leadService.deleteLeadTimeEffective(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@DeleteMapping(value="leadtime/effectives")
	public ResponseEntity<?> deleteLeadTimeEffective(Integer[] id) throws NotFoundException{
		leadService.deleteLeadTimeEffective(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	

	
	
}
