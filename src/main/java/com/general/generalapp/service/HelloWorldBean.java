package com.general.generalapp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldBean {
	  Logger logger = LoggerFactory.getLogger(HelloWorldBean.class);

   public void sayHello(){
	   logger.info("Logging here");
	   logger.debug("Debug here");
      System.out.println("Hello Spring Boot!");
      
   }
}
