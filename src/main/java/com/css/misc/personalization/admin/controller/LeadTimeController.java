package com.css.misc.personalization.admin.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.entity.pers.PersTmplCmpntLeadEffDte;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.service.LeadTimeService;

import io.micrometer.core.annotation.Timed;

@RestController
@Timed
@RequestMapping(value = "/")
public class LeadTimeController {
	@Autowired LeadTimeService leadTimeService;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<PersTmplCmpntLeadEffDte> handleNotFound(NotFoundException ex) {
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="leadtime/{id:\\d+}")
	public ResponseEntity<PersTmplCmpntLeadEffDte> get(@PathVariable("id") Integer id) throws NotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(leadTimeService.get(id));
	}
	
	@GetMapping(value="leadtimes")
	public ResponseEntity<PagedObjectList> getList(
			String suplrId,
			String tmplCmpntId,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize,
			@DateTimeFormat(pattern="yyyy-MM-dd" )Date timePoint,
			String regionCode) throws NotFoundException{
		PagedObjectList res = leadTimeService.getList(
				suplrId,tmplCmpntId,sortBy,sortDirection,pageNo,pageSize,timePoint,regionCode);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="leadtime",consumes = "application/json")
	public ResponseEntity<PersTmplCmpntLeadEffDte> create(@RequestBody PersTmplCmpntLeadEffDte obj){
		return ResponseEntity.status(HttpStatus.CREATED).body(leadTimeService.create(obj));
	}
	
	@PutMapping(value="leadtime/{id:\\d+}",consumes = "application/json")
	public ResponseEntity<PersTmplCmpntLeadEffDte> put(@PathVariable("id") Integer id,@RequestBody PersTmplCmpntLeadEffDte obj) {
		obj.setTmplCmpntEffId(id);
		return ResponseEntity.status(HttpStatus.OK).body(leadTimeService.update(obj));
	}
	
	@DeleteMapping(value="leadtime/{id:\\d+}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws NotFoundException{
		leadTimeService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@DeleteMapping(value="leadtimes")
	public ResponseEntity<?> delete(Integer[] id) throws NotFoundException{
		leadTimeService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	

	
	
}
