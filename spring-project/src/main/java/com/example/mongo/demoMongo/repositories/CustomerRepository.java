package com.example.mongo.demoMongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongo.demoMongo.data.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	List<Customer> findByFirstName(String firstName);	

}
