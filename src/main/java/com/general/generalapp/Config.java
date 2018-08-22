package com.general.generalapp;

import java.util.Collections;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
@Configuration
//@EnableAutoConfiguration
@EnableCouchbaseRepositories(basePackages={"com.general.generalapp.repository"})
public class Config extends AbstractCouchbaseConfiguration{
	  @Override
	    protected List<String> getBootstrapHosts() {
	        return Collections.singletonList("127.0.0.1");
	    }

	    @Override
	    protected String getBucketName() {
	        return "test_bucket";
	    }

	    @Override
	    protected String getBucketPassword() {
	        return "";
	    }
}
