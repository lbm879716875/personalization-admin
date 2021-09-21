package com.css.misc.personalization.admin.dao.pers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.css.misc.personalization.admin.entity.pers.PersAdminUser;

public interface UserDao extends JpaRepository<PersAdminUser,Integer>{
	public PersAdminUser findByUsername(String username);
}
