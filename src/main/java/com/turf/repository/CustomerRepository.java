package com.turf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turf.enities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
