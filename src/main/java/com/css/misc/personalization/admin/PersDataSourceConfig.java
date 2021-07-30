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
  entityManagerFactoryRef = "persEntityManagerFactory",
  transactionManagerRef = "persTransactionManager",	
  basePackages = {"com.css.misc.personalization.admin.dao.pers"})

public class PersDataSourceConfig {
	
	@Primary
	@Bean(name = "persDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.pers")
	public DataSourceProperties persDataSourceProperties() {
		return new DataSourceProperties();
    }
	
	@Primary
	@Bean(name="jpaProperties")
	@ConfigurationProperties(prefix = "spring.jpa")
	public Map<String, String> jpaProperties(){
		return new HashMap<String,String>();
	}
	
	@Primary
    @Bean(name = "persDataSource")
    @ConfigurationProperties("spring.datasource.pers")
    public DataSource persDataSource(@Qualifier("persDataSourceProperties") DataSourceProperties persDataSourceProperties) {
        return persDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
	
	@Primary
    @Bean(name = "persEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean persEntityManagerFactory(
            EntityManagerFactoryBuilder persEntityManagerFactoryBuilder, @Qualifier("persDataSource") DataSource persDataSource,
            @Qualifier("jpaProperties") Map<String, String> persJpaProperties ) {
        return persEntityManagerFactoryBuilder
                .dataSource(persDataSource)
                .packages("com.css.misc.personalization.admin.entity.pers")
                .persistenceUnit("persDataSource")
                .properties(persJpaProperties)
                .build();
    }
	
	@Primary
    @Bean(name = "persTransactionManager")
    public PlatformTransactionManager persTransactionManager(
            @Qualifier("persEntityManagerFactory") EntityManagerFactory persEntityManagerFactory) {
        return new JpaTransactionManager(persEntityManagerFactory);
    }
}
