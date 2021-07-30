package com.css.misc.personalization.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.constant.Db;
import com.css.misc.personalization.admin.constant.RegionCode;
import com.css.misc.personalization.admin.dao.pers.StaticDao;

@RestController
@RequestMapping("static")
public class StaticDataController {
	
	@Autowired
	private StaticDao staticDao;
	
	@GetMapping("suplerIds")
	public ResponseEntity<List<String>> getSuplerIds(){
		return ResponseEntity.ok(staticDao.getSuplrIdList());
	}
	
	@GetMapping("templateIds")
	public ResponseEntity<List<String>> getTemplateIds(){
		return ResponseEntity.ok(staticDao.getTmplIdList());
	}
	
	@GetMapping("componentIds")
	public ResponseEntity<List<String>> getComponentIds(){
		return ResponseEntity.ok(staticDao.getTmplCmpntIdList());
	}
	
	@GetMapping("regionCodes")
	public ResponseEntity<RegionCode[]> getRegionCodes(){
		return ResponseEntity.ok(RegionCode.values());
	}
	
	@GetMapping("dbs")
	public ResponseEntity<Db[]> getDbs(){
		return ResponseEntity.ok(Db.values());
	}
}
