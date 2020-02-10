package com.example.mongo.demoMongo.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mongo.demoMongo.data.Customer;
import com.example.mongo.demoMongo.repositories.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;

	@Test
	void should_get_customer() throws Exception {
		String id = "idcustomer";
		Customer customer = Customer.builder().firstName("first").lastName("last").build();
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

		Customer returned = customerService.getCustomer(id);

		assertAll("", () -> assertEquals(customer.getFirstName(), returned.getFirstName()),
				() -> assertEquals(customer.getLastName(), returned.getLastName()));
	}
	
	@Test
	void should_throw_exception_where_customer_not_found() {
		String id = "idcustomer";
		Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(Exception.class, () -> customerService.getCustomer(id));
	}

}
