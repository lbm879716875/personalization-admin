	package com.css.misc.personalization.admin;

import com.css.commons.idempotent.aspect.IdempotentAspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import(IdempotentAspect.class)
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, MongoAutoConfiguration.class })
@ComponentScan({ 
		"com.css.misc.personalization.admin",
		"com.css.commons.idempotent.properties"})
//@EnableEurekaClient
//@EnableFeignClients
@SpringBootConfiguration
public class Application {
	private static final Log LOG = LogFactory.getLog(Application.class);
	
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/apis");
		SpringApplication.run(Application.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		LOG.info("================================================================================");
		LOG.info("                               APPLICATION READY                                ");
		LOG.info("================================================================================");
	}
	
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:3000");
            }
        };
    }
	
	
}
