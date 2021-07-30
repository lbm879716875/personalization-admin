package com.css.misc.personalization.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CssSsoFilter cssSsoFilter;
//	@Autowired
//	private HttpFilter httpFilter;
	
	@Autowired
	private CssAuthenticationEntryPoint cssAuthenticationEntryPoint;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/leadtime**","/leadtime**/**").hasRole("ADMIN")
		.antMatchers("/authenticate","/signout").permitAll()
		.and().exceptionHandling().authenticationEntryPoint(cssAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(cssSsoFilter,BasicAuthenticationFilter.class)
		.httpBasic();
	}
	
}
