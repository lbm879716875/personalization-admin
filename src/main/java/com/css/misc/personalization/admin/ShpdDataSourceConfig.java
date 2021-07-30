package com.css.misc.personalization.admin;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "shpdEntityManagerFactory",
  transactionManagerRef = "shpdTransactionManager",	
  basePackages = {"com.css.misc.personalization.admin.dao.shpd"})
public class ShpdDataSourceConfig {
//	@Primary
	@Bean(name = "shpdDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.shpd")
	public DataSourceProperties shpdDataSourceProperties() {
		return new DataSourceProperties();
    }
	
//	@Primary
	@Bean(name="jpaProperties")
	@ConfigurationProperties(prefix = "spring.jpa")
	public Map<String, String> jpaProperties(){
		return new HashMap<String,String>();
	}
	
//	@Primary
    @Bean(name = "shpdDataSource")
    @ConfigurationProperties("spring.datasource.shpd")
    public DataSource shpdDataSource(@Qualifier("shpdDataSourceProperties") DataSourceProperties shpdDataSourceProperties) {
        return shpdDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
	
//	@Primary
    @Bean(name = "shpdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shpdEntityManagerFactory(
            EntityManagerFactoryBuilder shpdEntityManagerFactoryBuilder, @Qualifier("shpdDataSource") DataSource shpdDataSource,
            @Qualifier("jpaProperties") Map<String, String> shpdJpaProperties ) {
        return shpdEntityManagerFactoryBuilder
                .dataSource(shpdDataSource)
                .packages("com.css.misc.personalization.admin.entity.shpd","com.css.misc.personalization.admin.entity.common")
                .persistenceUnit("shpdDataSource")
                .properties(shpdJpaProperties)
                .build();
    }
	
//	@Primary
    @Bean(name = "shpdTransactionManager")
    public PlatformTransactionManager shpdTransactionManager(
            @Qualifier("shpdEntityManagerFactory") EntityManagerFactory shpdEntityManagerFactory) {
        return new JpaTransactionManager(shpdEntityManagerFactory);
    }
}
