package com.account.customer.controllers;


import java.util.Date;

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
import com.account.customer.repository.CustomerRepository;
import com.account.customer.services.CustomerServices;


@RestController
@CrossOrigin
@RequestMapping(value={"/api", "/source"})
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	CustomerServices customerServices = new CustomerServices();
	
	@GetMapping("/v1/customers/{cpf}")
	public CustomerModel getCustomer(@PathVariable(value="cpf") String customerCpf){
		return customerRepository.findByCpf(customerCpf);		
	}
	
	@DeleteMapping("/v1/customers")
	public String deleteCustomerAPI(@RequestBody CustomerModel customer) {
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = customerServices.validateCpf(customerCpf);
		if (validCpf) {
			if (registeredCustomer) {
				customerRepository.delete(customer);
				return "Cadastro de cliente excluido com sucesso";
			} else {
				return "Cadastro de cliente não encontrado";
			}
		} else {
			return "CPF inválido";
		}
	}
	
	@PutMapping("/v1/customers")
	public String updateCustomerAPI(@RequestBody CustomerModel customer) {
		if (customer.getCpf() == null || customer.getCpf() == "") {
			return "CPF é um campo obrigatório";
		}
		
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = customerServices.validateCpf(customerCpf);
		if (validCpf) {
			if (registeredCustomer) {
				Date date = new Date(System.currentTimeMillis()); 
				customer.setUpdatedDate(date);
				return customerRepository.save(customer) != null ? "Dados do cliente atualizados com sucesso" : "Ops, algo de errado aconteceu, tente novamente mais tarde";				
			} else {
				return "CPF informado ainda não possui cadastro";
			}
		} else {
			return "CPF inválido";
		}
	}
	
	@PostMapping("/v1/customers")
	public String createCustomerAPI(@RequestBody CustomerModel customer) {
		if (customer.getFirstName() == null || customer.getFirstName() == "") {
			return "firstName é um campo obrigatório";
		}
		
		if (customer.getLastName() == null || customer.getLastName() == "") {
			return "lastName é um campo obrigatório";
		}
		
		if (customer.getCpf() == null || customer.getCpf() == "") {
			return "CPF é um campo obrigatório";
		}
		
		if (customer.getDateOfBirth() == null) {
			return "DateOfBirth é um campo obrigatório";
		}
		
		if (customer.getEmail() != null && customer.getEmail() != "") {
			if (!customerServices.validateEmail(customer.getEmail())) {
				return "Email inválido";
			}
		}
		
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = customerServices.validateCpf(customerCpf);
		if (validCpf) {
			if (!registeredCustomer) {
				Date date = new Date(System.currentTimeMillis()); 
				customer.setUpdatedDate(date);
				customer.setCreatedDate(date);
				customerRepository.save(customer);
				return customerRepository.save(customer) != null ? "Cliente cadastrado com sucesso" : "Ops, algo de errado aconteceu, tente novamente mais tarde";				
			} else {
				return "CPF já cadastrado";
			}
		} else {
			return "CPF inválido";
		}
	}
	
	@PostMapping("/v2/customers")
	public String createCustomerAPIV2(@RequestBody CustomerModel customer) {
		if (customer.getAddress() == null || customer.getAddress() == "") {
			return "Endereço é um tempo obrigatório";
		}
		return createCustomerAPI(customer);
	}
	
	@GetMapping("")
	public String getGithubLink(){
		return "https://github.com/JoniMilczarek/CustomerApplication";	
	}
}