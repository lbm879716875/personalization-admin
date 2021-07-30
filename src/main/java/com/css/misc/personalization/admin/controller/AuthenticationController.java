package com.css.misc.personalization.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.css.misc.personalization.admin.model.AuthenticationRequest;
import com.css.misc.personalization.admin.model.AuthenticationResponse;

@RestController
public class AuthenticationController {
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
		//TODO
		System.out.println("login ok");
		return ResponseEntity.ok(new AuthenticationResponse("testtoken"));
	}
	
	@PostMapping(value = "/signout")
	public ResponseEntity<?> signout(@RequestBody String token){
		//TODO
		System.out.println(token + "signout ok");
		SecurityContextHolder.getContext().setAuthentication(null);
		return ResponseEntity.ok(new AuthenticationResponse("testtoken"));
	}
	
	@PostMapping(value = "/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody String token){
		//TODO
		System.out.println(token + "refresh ok");
		return ResponseEntity.ok(new AuthenticationResponse("newtoken"));
	}
	
	@PostMapping(value = "/validateSession")
	public ResponseEntity<Boolean> validateSession(@RequestBody String token){
		//TODO
		System.out.println(token + "validate ok");
		Boolean res = token==null||"".equals(token)||"null".equals(token)?false:true;
		return ResponseEntity.ok(res);
	}

}
