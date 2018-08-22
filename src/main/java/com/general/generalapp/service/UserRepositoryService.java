package com.general.generalapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;

import org.springframework.data.querydsl.binding.QuerydslPredicateBuilder;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
import com.general.generalapp.model.User;
import com.general.generalapp.repository.UserRepository;
import com.general.generalapp.util.AppUtil;

@Service
public class UserRepositoryService implements UserService {

	@Autowired
    private UserRepository repo; 
	
	@Autowired
    private CouchbaseTemplate  template;
	
	@Override
	public List<User> findAll() {
		 List<User> people = new ArrayList<>();
	        Iterator<User> it = repo.findAll().iterator();
	        while(it.hasNext()) {
	            people.add(it.next());
	        }
	        return people;
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return repo.findByFirstName(firstName);
	}

	@Override
	public List<User> findAllFromTemplate() {
		// TODO Auto-generated method stub
		return template.findByView(ViewQuery.from("user", "all"), User.class);
	}

	
	/**
	 * ViewQuery query = ViewQuery
  .from("studentGrades", "findByCourse")
  .keys(JsonArray.from("Math", "Science"));
  
ViewQuery query = ViewQuery.from("studentGrades", "findByGrade")
  .startKey(80)
  .endKey(89)
  .inclusiveEnd(true);
  
  ViewQuery query = ViewQuery.from("studentGrades", "findByGrade")
  .startKey(80)
  .endKey(90)
  .inclusiveEnd(false);
  
  ViewQuery query = ViewQuery
  .from("studentGrades", "findByGrade")
  .startKey(90);
  
  ViewQuery query = ViewQuery
  .from("studentGrades", "findByGrade")
  .endKey(60)
  .inclusiveEnd(false);
	 */
	@Override
	public List<User> findByFirstNameFromTemplate(String firstName) {
		// TODO Auto-generated method stub
		return template.findByView(ViewQuery.from("user", "byFirstName"), User.class);
	}

	@Override
	public List<User> findByFirstNameFromN1QL(String firstName) {
		// TODO Auto-generated method stub
		return repo.findByFirstNameN1QL(firstName);
	}

	@Override
	public List<User> findByFirstNameAndLastName(String firstName,String lastName) {
		// TODO Auto-generated method stub
		 Object[] objArr = new Object[2];
	        objArr[0] = firstName;
	        objArr[1] = lastName;

	        Query query=new  Query();
	        query.setKey(ComplexKey.of(objArr));
		
		return repo.findByFirstNameAndLastName(query);
	}
	
	
	public List<User> findByDobFromTemplate(Date from,Date to) {
	
		String dFrom = AppUtil.convertToDateString(from);
				String dTo= AppUtil.convertToDateString(to);
		ViewQuery query = ViewQuery.from("user", "byDob")
				  .startKey(dFrom)
				  .endKey(dTo)
				  .inclusiveEnd(true);
		return  template.findByView(query,User.class);
		
	}
	
	public List<User> findByDobWithStatusFromTemplate(Date from,Date to) {
		
		
		String dFrom = AppUtil.convertToDateString(from);
		String dTo= AppUtil.convertToDateString(to);
		String status ="SUCCESS";
ViewQuery query = ViewQuery.from("user", "byDob").key(JsonArray.from(dFrom, status))
		 // .startKey(JsonArray.from(dFrom, status))
		 // .endKey(JsonArray.from(dFrom,  status)).descending(true)
		  .inclusiveEnd(true);
System.out.println(query.getKeys());
return  template.findByView(query,User.class);
	}

	@Override
	public Map<String,Long> groupByDobFromTemplateCount(Date from, Date to) {
		String dFrom = AppUtil.convertToDateString(from);
		String dTo= AppUtil.convertToDateString(to);
		ViewQuery query = ViewQuery.from("user", "byDob")
				  .startKey(dFrom).endKey(dTo)
				  .reduce(true)
				  .groupLevel(1);
		
		Map<String,Long> countMap = new HashMap<>();
		ViewResult result = template.queryView(query);
		for(ViewRow row : result.allRows()) {
		    String dob = (String) row.key();
		    long count = Long.valueOf(row.value().toString());
		 countMap.put(dob, count);
		    //System.out.println("dob:"+dob+":"+sum);
		    //return  template.findByView(query,User.class);
	}
return countMap;
}}
