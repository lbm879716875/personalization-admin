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
import com.css.misc.personalization.admin.dao.pers.LeadDao;
import com.css.misc.personalization.admin.dao.pers.LeadTimeDao;
import com.css.misc.personalization.admin.dao.pers.StaticDao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc


public class ApplicationTests {
	
	@Autowired LeadTimeDao leadTimeDao;
	@Autowired RepoMapper repoMapper;
	@Autowired StaticDao staticDao;
	@Autowired PersDiscDescDao persDiscDescDao;
	@Autowired List<DataSource> list;
	@Autowired LeadDao leadDao;
	
	@Test
	public void test()   {
		System.out.print(leadDao.count());
	}
}
