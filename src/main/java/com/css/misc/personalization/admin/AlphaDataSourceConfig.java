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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "alphaEntityManagerFactory",
  transactionManagerRef = "alphaTransactionManager",	
  basePackages = {"com.css.misc.personalization.admin.dao.alpha"})
public class AlphaDataSourceConfig {
//	@Primary
	@Bean(name = "alphaDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.alpha")
	public DataSourceProperties alphaDataSourceProperties() {
		return new DataSourceProperties();
    }
	
//	@Primary
	@Bean(name="jpaProperties")
	@ConfigurationProperties(prefix = "spring.jpa")
	public Map<String, String> jpaProperties(){
		return new HashMap<String,String>();
	}
	
//	@Primary
    @Bean(name = "alphaDataSource")
    @ConfigurationProperties("spring.datasource.alpha")
    public DataSource alphaDataSource(@Qualifier("alphaDataSourceProperties") DataSourceProperties alphaDataSourceProperties) {
        return alphaDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
	
//	@Primary
    @Bean(name = "alphaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean alphaEntityManagerFactory(
            EntityManagerFactoryBuilder alphaEntityManagerFactoryBuilder, @Qualifier("alphaDataSource") DataSource alphaDataSource,
            @Qualifier("jpaProperties") Map<String, String> alphaJpaProperties ) {
        return alphaEntityManagerFactoryBuilder
                .dataSource(alphaDataSource)
                .packages("com.css.misc.personalization.admin.entity.alpha","com.css.misc.personalization.admin.entity.common")
                .persistenceUnit("alphaDataSource")
                .properties(alphaJpaProperties)
                .build();
    }
	
//	@Primary
    @Bean(name = "alphaTransactionManager")
    public PlatformTransactionManager alphaTransactionManager(
            @Qualifier("alphaEntityManagerFactory") EntityManagerFactory alphaEntityManagerFactory) {
        return new JpaTransactionManager(alphaEntityManagerFactory);
    }
}
