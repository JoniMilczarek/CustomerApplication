package com.account.customer.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.customer.model.CustomerModel;
import com.account.customer.services.CustomerServices;


@RestController
@CrossOrigin
@RequestMapping(value={"/api", "/source"})
public class CustomerController {
	@Autowired
	CustomerServices customerServices;
	
	@GetMapping("/v1/customers/{cpf}")
	public CustomerModel getCustomer(@PathVariable(value="cpf") String customerCpf){
		return customerServices.getCustomer(customerCpf);	
	}
	
	@DeleteMapping("/v1/customers")
	public String deleteCustomerAPI(@RequestBody CustomerModel customer) {
		return customerServices.deleteCustomer(customer);
	}
	
	@PutMapping("/v1/customers")
	public String updateCustomerAPI(@RequestBody CustomerModel customer) {
		return customerServices.updateCustomer(customer);
	}
	
	@PostMapping("/v1/customers")
	public String createCustomerAPI(@RequestBody CustomerModel customer) {
		return customerServices.createCustomerV1(customer);
	}
	
	@PostMapping("/v2/customers")
	public String createCustomerAPIV2(@RequestBody CustomerModel customer) {
		return customerServices.createCustomerV2(customer);
	}
	
	@GetMapping("")
	public String getGithubLink(){
		return "https://github.com/JoniMilczarek/CustomerApplication";	
	}
}