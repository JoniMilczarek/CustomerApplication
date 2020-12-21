package com.account.customer.services;

import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.customer.model.CustomerModel;
import com.account.customer.repository.CustomerRepository;

@Service
public class CustomerServices {
	@Autowired
	CustomerRepository customerRepository;
		
	public CustomerModel getCustomer(String customerCpf) {
		return customerRepository.findByCpf(customerCpf);
	}
	
	public String deleteCustomer(CustomerModel customer) {
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = validateCpf(customerCpf);
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
	
	public String createCustomerV1(CustomerModel customer) {
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
			if (!validateEmail(customer.getEmail())) {
				return "Email inválido";
			}
		}
		
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = validateCpf(customerCpf);
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
	
	public String createCustomerV2(CustomerModel customer) {
		if (customer.getAddress() == null || customer.getAddress() == "") {
			return "Endereço é um tempo obrigatório";
		}
		return createCustomerV1(customer);
	}
	
	public String updateCustomer(CustomerModel customer) {
		if (customer.getCpf() == null || customer.getCpf() == "") {
			return "CPF é um campo obrigatório";
		}
		
		String customerCpf = customer.getCpf();
		Boolean registeredCustomer = customerRepository.findByCpf(customerCpf) != null ? true : false;
		Boolean validCpf = validateCpf(customerCpf);
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
	
	public Boolean validateCpf(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';				
			} else {
				dig11 = (char) (r + 48);
			}

			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (Exception erro) {
			return (false);
		}
	}
	
	public Boolean validateEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
}
