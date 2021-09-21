package com.css.misc.personalization.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.entity.pers.PersAdminUser;
import com.css.misc.personalization.admin.util.UserCache;

@RestController
@RequestMapping(value="/userinfo")
public class UserInfoController {
	@Autowired UserCache userCache;
	@Autowired HttpServletRequest request;
	
	@GetMapping(value="/name")
	public ResponseEntity<Map> getUsername(){
		Map<String,String> res = new HashMap<>();
		res.put("username", (String)request.getAttribute(UserCache.usernameHeadername));
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping(value="/role")
	public ResponseEntity<List<String>> getUserRole(){
		String username = (String)request.getAttribute(UserCache.usernameHeadername);
		PersAdminUser user = userCache.get(username);
		List<String> roleList = user.getRoleList().stream().map(u->u.getRoleName()).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(roleList);
	}
	
	@GetMapping(value="/number")
	public ResponseEntity<Map> getEmplNbr(){
		String username = (String)request.getAttribute(UserCache.usernameHeadername);
		PersAdminUser user = userCache.get(username);
		Map<String,String> res = new HashMap<>();
		res.put("number", user.getEmplNbr());
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
