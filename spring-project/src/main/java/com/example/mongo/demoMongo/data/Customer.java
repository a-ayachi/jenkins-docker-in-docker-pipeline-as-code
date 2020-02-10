package com.example.mongo.demoMongo.data;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
	public String id;
	
	@NotBlank(message = "firstName is mandatory")
	public String firstName;
	@NotBlank(message = "lastName is mandatory")
	public String lastName;

}
