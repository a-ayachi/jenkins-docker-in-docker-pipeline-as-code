package com.example.mongo.demoMongo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerWithMockMvcIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void should_get_all_customers() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/customers")).andExpect(MockMvcResultMatchers.status().isOk());

	}

}
