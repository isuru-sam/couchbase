package com.general.generalapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.general.generalapp.model.User;
import com.general.generalapp.service.HelloWorldBean;
import com.general.generalapp.service.UserRepositoryService;
import com.general.generalapp.util.AppUtil;

/*
 * https://www.baeldung.com/couchbase-query-mapreduce-view
 */

/*
 * https://www.baeldung.com/spring-data-couchbase
 */
@SpringBootApplication
public class GeneralappApplication {

	
	public static void main(String[] args) throws Exception{
		ApplicationContext ctx = SpringApplication.run(GeneralappApplication.class, args);
		List<User> users = new ArrayList<>();
	//	HelloWorldBean bean = ctx.getBean(HelloWorldBean.class);
	      //bean.sayHello();
	      System.out.println("start");
	      UserRepositoryService ux =  ctx.getBean(UserRepositoryService.class);
	     //  users = ux.findAll();
	// users = ux.findAllFromTemplate();
	//     users=     ux.findByFirstNameFromTemplate("Mark");
	    //  users=     ux.findByFirstName("Mark");
	     
	      System.out.println(AppUtil.convertToDateString(new Date()));
	  //   users=     ux.findByFirstNameFromN1QL("Mark");
	     //  users=     ux.findByFirstNameAndLastName("Mark", "Taylor"); 
	    // users=ux.findByDobFromTemplate(AppUtil.getDate("2000-01-01"), AppUtil.getDate("2000-01-01"));
	     users=ux.findByDobWithStatusFromTemplate(AppUtil.getDate("2000-02-02"), AppUtil.getDate("2000-02-02")); 
	      // Map<String,Long> userCounts=     ux.groupByDobFromTemplateCount(AppUtil.getDate("2000-01-01"), AppUtil.getDate("2000-01-01"));
	      //System.out.println(Arrays.asList(userCounts));
	      
	      for(User u : users) {
		System.out.println(u.getId());
		System.out.println(u.getStatus());
		System.out.println(u.getFirstName());
		System.out.println(u.getFirstnames());
		System.out.println(u.getChildrenAges());
	}
	
	System.out.println("Size is "+users.size());
	
	
	}
}
