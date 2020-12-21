package com.account.customer.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.account.customer.controllers.CustomerController;
import com.account.customer.model.CustomerModel;
import com.account.customer.services.CustomerServices;

import io.restassured.http.ContentType;

@SpringBootTest
public class CustomerControllerTest {

	    @Autowired
	    private CustomerController customerController;
	    
	    @MockBean
	    private CustomerServices customerService;

	    @BeforeEach
	    public void setup() {
	    	standaloneSetup(this.customerController);
	    }

	    @Test
	    public void mustReturnCustomerSuccess_WhereGetCustomer() {
	    	when(this.customerService.getCustomer("04515784064")).thenReturn(new CustomerModel("04515784064", "Jonatan", "Milczarek", "Masculino", "jonatanmilc@gmail.com", "brazilian", "Brazil", "enredeco qualquer", new Date(), new Date(), new Date()));
	    	given().accept(ContentType.JSON).when().get("/source/v1/customers/{cpf}", "04515784064").then().statusCode(HttpStatus.OK.value());
	    }
	}