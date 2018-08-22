package com.general.generalapp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.general.generalapp.model.User;

public interface UserService {
	List<User> findAll();
    List<User> findByFirstName(String firstName);
    List<User> findByFirstNameAndLastName(String firstName,String lastName);
    List<User> findAllFromTemplate();
    List<User> findByFirstNameFromTemplate(String firstName);
    List<User> findByFirstNameFromN1QL(String firstName);
    List<User> findByDobFromTemplate(Date from,Date to);
    public List<User> findByDobWithStatusFromTemplate(Date from,Date to);
   Map<String,Long> groupByDobFromTemplateCount(Date from,Date to);
    
}
