package com.css.misc.personalization.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sysinfo")
public class SysInfoController {
	@Value("${app.version}")
	String appVersion;
	
	@Value("${app.env}")
	String env;
	
	@Value("${app.logoutUrl}")
	String logoutUrl;
	
	@GetMapping(value="/version")
	public ResponseEntity<Map> getAppVersion(){
		Map<String,String> res = new HashMap<>();
		res.put("version", appVersion);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping(value="/env")
	public ResponseEntity<Map> getAppEnv(){
		Map<String,String> res = new HashMap<>();
		res.put("env", env);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping(value="/logoutUrl")
	public ResponseEntity<Map> getLogoutUrl(){
		Map<String,String> res = new HashMap<>();
		res.put("logoutUrl", logoutUrl);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
