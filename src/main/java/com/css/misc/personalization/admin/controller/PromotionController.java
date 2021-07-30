package com.css.misc.personalization.admin.controller;

import java.util.Date;
import java.util.List;

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

import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.SortDirection;
import com.css.misc.personalization.admin.entity.common.PersDiscDesc;
import com.css.misc.personalization.admin.entity.common.PersInvntDisc;
import com.css.misc.personalization.admin.model.PagedObjectList;
import com.css.misc.personalization.admin.model.PersInvntDiscListRequest;
import com.css.misc.personalization.admin.model.pk.PersInvntDiscPK;
import com.css.misc.personalization.admin.service.PromotionService;

import io.micrometer.core.annotation.Timed;

@RestController
@Timed
@RequestMapping(value = "/db/{db}")
public class PromotionController {
	@Autowired PromotionService promotionService;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/disc-desc/{discTag}")
	public ResponseEntity<PersDiscDesc> get(@PathVariable("db") Db db,@PathVariable("discTag") String discTag) throws NotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.getDiscDesc(db,discTag));
	}
	
	@GetMapping(value="/disc-descs")
	public ResponseEntity<PagedObjectList> getDiscDescList(
			@PathVariable("db") Db db,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize
			) throws NotFoundException{
		PagedObjectList res = promotionService.getDiscDescList(
				db,sortBy,sortDirection,pageNo,pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="/disc-desc",consumes = "application/json")
	public ResponseEntity<PersDiscDesc> create(@PathVariable("db") Db db,@RequestBody PersDiscDesc persDiscDesc){
		return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.createDiscDesc(db,persDiscDesc));
	}
	
	@PutMapping(value="/disc-desc/{discTag}",consumes = "application/json")
	public ResponseEntity<PersDiscDesc> put(@PathVariable("db") Db db,@PathVariable("discTag") String discTag,@RequestBody PersDiscDesc persDiscDesc){
		persDiscDesc.setDiscTag(discTag);
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.updateDiscDesc(db,persDiscDesc));
	}
	
	@DeleteMapping(value="/disc-desc/{discTag}")
	public ResponseEntity<?> delete(@PathVariable("db") Db db,@PathVariable("discTag") String discTag) throws NotFoundException{
		promotionService.deleteDiscDesc(db,discTag);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@DeleteMapping(value="/disc-descs}")
	public ResponseEntity<?> delete(@PathVariable("db") Db db,String[] discTags) throws NotFoundException{
		promotionService.deleteDiscDesc(db,discTags);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@GetMapping(value="/invnt-disc/{discTag}")
	public ResponseEntity<PagedObjectList> getInvntDiscList(
			@PathVariable("db") Db db,
			@PathVariable("discTag") String discTag,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize
			) {
		PagedObjectList res = promotionService.getInvntDiscList(
				db,discTag,sortBy,sortDirection,pageNo,pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="/invnt-disc",consumes = "application/json")
	public ResponseEntity<List<PersInvntDisc>> createInvntDisc(@PathVariable("db") Db db,@RequestBody PersInvntDiscListRequest listReq){
		List<PersInvntDisc> list = listReq.toPersInvntDiscList();
		promotionService.createInvntDisc(db,list);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PutMapping(value="/invnt-disc/{discTag}",consumes = "application/json")
	public ResponseEntity<PersInvntDisc> updateInvntDisc(@PathVariable("db") Db db,@PathVariable("discTag") String discTag,@RequestBody PersInvntDisc invntDisc){
		invntDisc.setDiscTag(discTag);
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.updateInvntDisc(db,invntDisc));
	}
	
	@DeleteMapping(value="/invnt-disc")
	public ResponseEntity<?> deleteInvntDisc(@PathVariable("db") Db db,PersInvntDiscPK pk) throws NotFoundException {
		promotionService.deleteInvntDisc(db,pk);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping(value="/invnt-disc/{discTag}")
	public ResponseEntity<?> deleteInvntDisc(@PathVariable("db") Db db,@PathVariable("discTag") String discTag) throws NotFoundException{
		promotionService.deleteInvntDisc(db,discTag);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
