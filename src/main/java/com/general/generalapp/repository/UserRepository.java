package com.general.generalapp.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;

import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import com.couchbase.client.protocol.views.Query;
import com.general.generalapp.model.User;


//@N1qlPrimaryIndexed
//@ViewIndexed(designDoc = "user")
public interface UserRepository extends CrudRepository<User, String> {
	//@View(viewName="firstName")   
	//@View
	@View(designDocument = "user", viewName = "byFirstName")
    List<User> findByFirstName(String firstName);
	
	@View(designDocument = "user", viewName = "byFirstNameAndLastName")
    List<User> findByFirstNameAndLastName(Query query);
	
	
	
	 //@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and firstName = $1 within #{#n1ql.bucket}")	    
	// @Query("#{#n1ql.selectEntity} where firstName like $1 AND #{#n1ql.filter} within #{#n1ql.bucket}")	   
	 @org.springframework.data.couchbase.core.query.Query("#{#n1ql.selectEntity} WHERE firstName = $1 AND #{#n1ql.filter}")
	List<User> findByFirstNameN1QL(String firstName);
		
		
	
	
	
	
/*    List<User> findByFirstNameN1QL(String firstName);
    
    
    @Query("$SELECT_ENTITY$ WHERE firstname LIKE "%ck%")
    		List<UserInfo> findPatrickAndJackAmongOthers();*/
}
