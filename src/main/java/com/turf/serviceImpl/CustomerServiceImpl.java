package com.turf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.turf.enities.Customer;
import com.turf.repository.CustomerRepository;
import com.turf.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}

}
