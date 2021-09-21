package com.css.misc.personalization.admin.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.Region;
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
public class PromotionController {
	@Autowired PromotionService promotionService;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
	    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/disc-desc/{discTag}")
	public ResponseEntity<PersDiscDesc> get(Region region,@PathVariable("discTag") String discTag) throws NotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.getDiscDesc(region.db,discTag));
	}
	
	@GetMapping(value="/disc-descs")
	public ResponseEntity<PagedObjectList> getDiscDescList(
			Region region,
			String[] sortBy,
			SortDirection[] sortDirection,
			Integer pageNo,
			Integer pageSize
			) throws NotFoundException{
		PagedObjectList res = promotionService.getDiscDescList(
				region,sortBy,sortDirection,pageNo,pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PostMapping(value="/disc-desc",consumes = "application/json")
	public ResponseEntity<PersDiscDesc> create(@RequestParam(required = true) Region region,@RequestBody PersDiscDesc persDiscDesc){
		return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.createDiscDesc(region.db,persDiscDesc));
	}
	
	@PutMapping(value="/disc-desc/{discTag}",consumes = "application/json")
	public ResponseEntity<PersDiscDesc> put(@RequestParam(required = true) Region region,@PathVariable("discTag") String discTag,@RequestBody PersDiscDesc persDiscDesc){
		persDiscDesc.setDiscTag(discTag);
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.updateDiscDesc(region.db,persDiscDesc));
	}
	
	@DeleteMapping(value="/disc-desc/{discTag}")
	public ResponseEntity<?> delete(@RequestParam(required = true) Db db,@PathVariable("discTag") String discTag) throws NotFoundException{
		promotionService.deleteDiscDesc(db,discTag);
		return ResponseEntity.status(HttpStatus.OK).body(null);	
	}
	
	@DeleteMapping(value="/disc-descs")
	public ResponseEntity<?> delete(@RequestParam(required = true) Db db,String[] discTags) throws NotFoundException{
		promotionService.deleteDiscDesc(db,discTags);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping(value="/invnt-disc/{discTag}")
	public ResponseEntity<PagedObjectList> getInvntDiscList(
			@RequestParam(required = true) Db db,
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
	public ResponseEntity<List<PersInvntDisc>> createInvntDisc(@RequestParam(required = true) Db db,@RequestBody PersInvntDiscListRequest listReq){
		List<PersInvntDisc> list = listReq.toPersInvntDiscList();
		promotionService.createInvntDisc(db,list);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@PutMapping(value="/invnt-disc/{discTag}",consumes = "application/json")
	public ResponseEntity<PersInvntDisc> updateInvntDisc(@RequestParam(required = true) Db db,@PathVariable("discTag") String discTag,@RequestBody PersInvntDisc invntDisc){
		invntDisc.setDiscTag(discTag);
		return ResponseEntity.status(HttpStatus.OK).body(promotionService.updateInvntDisc(db,invntDisc));
	}
	
	@DeleteMapping(value="/invnt-disc")
	public ResponseEntity<?> deleteInvntDisc(@RequestParam(required = true) Db db,PersInvntDiscPK pk) throws NotFoundException {
		promotionService.deleteInvntDisc(db,pk);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping(value="/invnt-disc/{discTag}")
	public ResponseEntity<?> deleteInvntDisc(@RequestParam(required = true) Db db,@PathVariable("discTag") String discTag) throws NotFoundException{
		promotionService.deleteInvntDisc(db,discTag);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
