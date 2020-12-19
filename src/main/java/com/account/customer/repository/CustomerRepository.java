package com.account.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.customer.model.CustomerModel;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
	
    CustomerModel findByCpf(String cpf);

}