package com.css.misc.personalization.admin;

import java.util.List;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.css.misc.personalization.admin.util.RepoMapper;
import com.css.misc.personalization.admin.dao.alpha.PersDiscDescDao;
import com.css.misc.personalization.admin.dao.pers.ActionNameDao;
import com.css.misc.personalization.admin.dao.pers.TmplCmpntLeadDao;
import com.css.misc.personalization.admin.dao.pers.TmplCmpntLeadEffDao;
import com.css.misc.personalization.admin.dao.pers.PrivilegeDao;
import com.css.misc.personalization.admin.dao.pers.RoleDao;
import com.css.misc.personalization.admin.dao.pers.StaticDao;
import com.css.misc.personalization.admin.dao.pers.UserDao;
import com.css.misc.personalization.admin.entity.pers.PersAdminPrivilege;
import com.css.misc.personalization.admin.entity.pers.PersAdminRole;
import com.css.misc.personalization.admin.entity.pers.PersAdminUser;
import com.css.misc.personalization.admin.model.pk.ActionNamePK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc


public class ApplicationTests {
	
	@Autowired TmplCmpntLeadEffDao leadTimeDao;
	@Autowired RepoMapper repoMapper;
	@Autowired StaticDao staticDao;
	@Autowired PersDiscDescDao persDiscDescDao;
	@Autowired List<DataSource> list;
	@Autowired TmplCmpntLeadDao leadDao;
	@Autowired UserDao userDao;
	@Autowired RoleDao roleDao;
	@Autowired PrivilegeDao privilegeDao;
	@Autowired ActionNameDao actionNameDao;
	
	@Test
	public void test()   {
		System.out.println(actionNameDao.findById(new ActionNamePK("/leadtime/{id:\\d+}","PUT")).orElseThrow().getActionName());
	}
}
