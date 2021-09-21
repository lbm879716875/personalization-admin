package com.css.misc.personalization.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.css.misc.personalization.admin.entity.pers.PersAdminRole;
import com.css.misc.personalization.admin.entity.pers.PersAdminUser;
import com.css.misc.personalization.admin.util.UserCache;



@Component
public class CssSecurityFilter extends OncePerRequestFilter{
	@Autowired UserCache userCache;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//DEV,UAT,PROD CAS SSO
		String username = request.getHeader("principal");
		//LOCAL
//		String username = "ming.lin.b";
//		String username = "john.wong.c.c";
//		String username = "ricky.liu.h.m";
		
	//////////	
		request.setAttribute(UserCache.usernameHeadername, username);
		request.setAttribute(UserCache.actionId, UUID.randomUUID().toString());
		PersAdminUser user = userCache.get(username);
		if(user==null)
			throw new ServletException("User not exist");
		
		String path = request.getServletPath();
		if(new AntPathMatcher().match("/leadtims", path)) {
//			System.out.println("/leadtimes");
		}
		
		

		List<SimpleGrantedAuthority> sl = new ArrayList<>();
		for(PersAdminRole role:user.getRoleList()) {
			sl.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		}
		UserDetails userDetails = new User(username, "",
				sl);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);
	}
	
	

}
