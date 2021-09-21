package com.css.misc.personalization.admin.util;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.css.misc.personalization.admin.dao.pers.UserDao;
import com.css.misc.personalization.admin.entity.pers.PersAdminUser;

@Component
public class UserCache {
	public static final String usernameHeadername="principal";
	public static final String actionId="actionId";
	@Autowired UserDao userDao;
	protected static ConcurrentHashMap<String,Value> cacheMap = new ConcurrentHashMap<>();
	private static Integer effectiveTime = 15*60*1000;//15 mins
	
	
	public PersAdminUser get(String username) {
		synchronized(username){
			PersAdminUser user=null;
			//cached and within effective time
			if(cacheMap.containsKey(username)) {
				Value value = cacheMap.get(username);
				if(new Date().getTime()-value.getRecordTime().getTime()<effectiveTime) {
					System.out.println(username+" found in cache,effective time remain " +(effectiveTime-(new Date().getTime()-value.getRecordTime().getTime())));
					user = value.getUser();
				}
				else {
					user = getUserFromDbAndCache(username);
				}
			}else {
				user = getUserFromDbAndCache(username);
			}
			return user;
		}
	}
	
	private class Value{
		private PersAdminUser user;
		private Date recordTime;
		
		public Value(PersAdminUser user, Date recordTime) {
			super();
			this.user = user;
			this.recordTime = recordTime;
		}
		public PersAdminUser getUser() {
			return user;
		}
		public void setUser(PersAdminUser user) {
			this.user = user;
		}
		public Date getRecordTime() {
			return recordTime;
		}
		public void setRecordTime(Date recordTime) {
			this.recordTime = recordTime;
		}
		

		
	}

	private PersAdminUser getUserFromDbAndCache(String username) {
		System.out.println(username + " get user from db and cache");
		PersAdminUser user=userDao.findByUsername(username);
		Value value = new Value(user,new Date());
		cacheMap.put(username, value);
		return user;
	}
}
