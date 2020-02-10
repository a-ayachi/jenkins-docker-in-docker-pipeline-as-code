package com.example.mongo.demoMongo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongo.demoMongo.data.Customer;
import com.example.mongo.demoMongo.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer getCustomer(String id) throws Exception {
		return customerRepository.findById(id).orElseThrow(Exception::new);
	}
	
	public List<Customer> getAll() {
	
		return customerRepository.findAll();
	}
	
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void delete(String id) {
		customerRepository.deleteById(id);
	}

}
