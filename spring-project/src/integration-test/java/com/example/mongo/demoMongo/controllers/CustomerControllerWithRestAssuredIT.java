package com.example.mongo.demoMongo.controllers;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.example.mongo.demoMongo.data.Customer;
import com.example.mongo.demoMongo.repositories.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerWithRestAssuredIT {

	@LocalServerPort
	int port;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void before() {
		RestAssured.port = port;
		customerRepository.deleteAll();
	}

	@Test
	void should_get_all_customers() throws Exception {
		Customer customer = Customer.builder().firstName("myfirstName").lastName("mylastName").build();

		customer = customerRepository.save(customer);
		RestAssured.get("/customers").then().contentType(ContentType.JSON).body("$", Matchers.hasSize(1))
				.statusCode(HttpStatus.OK.value());

	}

	@Test
	void should_create_customer() throws JsonProcessingException {

		Customer customer = Customer.builder().firstName("myfirstName").lastName("mylastName").build();

		RestAssured.given().body(objectMapper.writeValueAsString(customer)).contentType(ContentType.JSON)
				.post("/customers").then().contentType(ContentType.JSON)
				.body("firstName", IsEqual.equalTo("myfirstName")).body("lastName", IsEqual.equalTo("mylastName"))
				.body("firstName", IsNull.notNullValue());

	}

	@Test
	void should_delete_customer() {
		Customer customer = Customer.builder().firstName("myfirstName").lastName("mylastName").build();

		customer = customerRepository.save(customer);

		RestAssured.given().pathParam("id", customer.getId()).delete("/customers/{id}").then()
				.statusCode(HttpStatus.OK.value());

		Assertions.assertThat(customerRepository.findAll()).hasSize(0);

	}

}
