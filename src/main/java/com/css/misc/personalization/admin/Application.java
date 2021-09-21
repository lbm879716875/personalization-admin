	package com.css.misc.personalization.admin;

import com.css.commons.idempotent.aspect.IdempotentAspect;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
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
	
	//only 8009 for ajp
//	 @Bean
//	 public ServletWebServerFactory servletContainer() {
//	        TomcatServletWebServerFactory tomcat = new 
//	   TomcatServletWebServerFactory() {
//	            @Override
//	            protected void postProcessContext(Context context) {
//	                SecurityConstraint securityConstraint = new SecurityConstraint();
//	                securityConstraint.setUserConstraint("CONFIDENTIAL");
//	                SecurityCollection collection = new SecurityCollection();
//	                collection.addPattern("/*");
//	                securityConstraint.addCollection(collection);
//	                context.addConstraint(securityConstraint);
//	            }
//	        };
//	        tomcat.addAdditionalTomcatConnectors(redirectConnector());
//
//	        return tomcat;
//	    }

    
    //both 8009 for ajp and  port (8080 or 10005)
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
      return server -> {
        if (server instanceof TomcatServletWebServerFactory) {
            ((TomcatServletWebServerFactory) server).addAdditionalTomcatConnectors(redirectConnector());
        }
      };
    }

    private Connector redirectConnector() {
       Connector connector = new Connector("AJP/1.3");
       connector.setScheme("http");
       connector.setPort(8002);
       connector.setSecure(false);
       connector.setAllowTrace(false);
       connector.setProperty("address", "0.0.0.0");
       return connector;
    }
	
	
}
