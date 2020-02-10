package com.example.mongo.demoMongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mongo.demoMongo.data.Customer;
import com.example.mongo.demoMongo.repositories.CustomerRepository;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class DemoMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(CustomerRepository customerRepository) {
		return (args) -> {
			log.info("populate some customers");

			customerRepository.save(Customer.builder().lastName("lastName1").firstName("firstName1").build());
			customerRepository.save(Customer.builder().lastName("lastName2").firstName("firstName2").build());
			customerRepository.save(Customer.builder().lastName("lastName3").firstName("firstName3").build());

			log.info("end customer population");

		};
	}

}
