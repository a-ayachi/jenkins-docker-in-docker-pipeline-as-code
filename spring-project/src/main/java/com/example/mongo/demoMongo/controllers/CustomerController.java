package com.example.mongo.demoMongo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.mongo.demoMongo.data.Customer;
import com.example.mongo.demoMongo.services.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Log
public class CustomerController {
	
	private final CustomerService customerService;
	
	@GetMapping
	public List<Customer> getAll() {		
		return customerService.getAll();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") String id) {
		try {
			
			return customerService.getCustomer(id);
		} catch (Exception e) {
			log.info("customer not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found");
		}
	}
	
	@PostMapping
	public Customer addCustomer(@Valid @RequestBody Customer customer) {		
		return customerService.createCustomer(customer);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") String id) {		
		customerService.delete(id);		
	}	

}
