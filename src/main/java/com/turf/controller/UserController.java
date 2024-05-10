package com.turf.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.turf.enities.Customer;
import com.turf.repository.CustomerRepository;
import com.turf.service.CustomerService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomerRepository customerRepository;
		
	@Autowired
	private CustomerService customerService;
	
	

	@GetMapping("/mybooking")
	public String mybooking(Model model, Principal principal) {

		String name = principal.getName();

		Customer customer = customerRepository.findByEmail(name);
		/* model.addAttribute("customers", customerService.getAllCustomers()); */
		model.addAttribute("customer", customer);
		return "user/mybooking";
	}
	
	/*
	 * @GetMapping("/userbase") public String allcustomer(Model model) {
	 * 
	 * model.addAttribute("customers", customerService.getAllCustomers()); return
	 * "user/userbase"; }
	 */



}
