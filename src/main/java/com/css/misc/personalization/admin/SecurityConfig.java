package com.css.misc.personalization.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.css.misc.personalization.admin.dao.pers.PrivilegeDao;
import com.css.misc.personalization.admin.entity.pers.PersAdminPrivilege;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CssSecurityFilter cssSecurityFilter;
	
	@Autowired PrivilegeDao privilegeDao;
	
	@Autowired
	private CssAuthenticationEntryPoint cssAuthenticationEntryPoint;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		List<PersAdminPrivilege> privilegeList = privilegeDao.findAll();
		List<Privilege> groupedList = groupPrivileges(privilegeList);
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry e = 
				http.csrf().disable().authorizeRequests();
		for(Privilege p :groupedList) {
			System.out.println(p);
			e.antMatchers(HttpMethod.valueOf(p.getHttpMethod()), p.getPattern()).hasAnyRole(p.getRoleNameSet().toArray(String[]::new));
		}


		e
		.anyRequest().denyAll()
		.and().exceptionHandling().authenticationEntryPoint(cssAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(cssSecurityFilter,BasicAuthenticationFilter.class)
		.httpBasic();
	}
	
	private class Privilege{
		private String httpMethod;
		private String pattern;
		private Set<String> roleNameSet;
		
		public Privilege(String httpMethod,String pattern) {
			this.httpMethod = httpMethod;
			this.pattern = pattern;
			this.roleNameSet = new HashSet<>();
		}
		public String getHttpMethod() {
			return httpMethod;
		}
		public void setHttpMethod(String httpMethod) {
			this.httpMethod = httpMethod;
		}
		public String getPattern() {
			return pattern;
		}
		public void setPattern(String pattern) {
			this.pattern = pattern;
		}
		public Set<String> getRoleNameSet() {
			return roleNameSet;
		}
		public void setRoleNameSet(Set<String> roleNameSet) {
			this.roleNameSet = roleNameSet;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
			result = prime * result + ((httpMethod == null) ? 0 : httpMethod.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Privilege other = (Privilege) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (pattern == null) {
				if (other.pattern != null)
					return false;
			} else if (!pattern.equals(other.pattern))
				return false;
			if (httpMethod == null) {
				if (other.httpMethod != null)
					return false;
			} else if (!httpMethod.equals(other.httpMethod))
				return false;
			return true;
		}
		private SecurityConfig getEnclosingInstance() {
			return SecurityConfig.this;
		}
		@Override
		public String toString() {
			return "Privilege [httpMethod=" + httpMethod + ", pattern=" + pattern + ", roleNameSet=" + roleNameSet
					+ "]";
		}
		
		
	}
	
	private List<Privilege> groupPrivileges(List<PersAdminPrivilege> privilegeList){
		List<Privilege> groupedList = new ArrayList<>();
		for(PersAdminPrivilege privilege:privilegeList ) {
			String pattern = privilege.getPattern();
			String httpMethod = privilege.getHttpMethod();
			List<String> roleList = privilege.getRoleList().stream().map(e->e.getRoleName()).collect(Collectors.toList());
			if(httpMethod!=null) {
				add(httpMethod,pattern,roleList,groupedList);
			}else {
				for(HttpMethod method:HttpMethod.values()) {
					add(method.name(),pattern,roleList,groupedList);
				}
			}
			
		}
		return groupedList;
	}
	
	private void add(String httpMethod,String pattern,List<String> roleList,List<Privilege> groupedList) {
		Privilege p = new Privilege(httpMethod,pattern);
		p.getRoleNameSet().addAll(roleList);
		add(p,groupedList);
	}
	
	private void add(Privilege privilege,List<Privilege> groupedList) {
		Integer index = groupedList.indexOf(privilege);
		if(index==-1) {
			groupedList.add(privilege);
		}else {
			groupedList.get(index).getRoleNameSet().addAll(privilege.getRoleNameSet());
		}
	}
}
