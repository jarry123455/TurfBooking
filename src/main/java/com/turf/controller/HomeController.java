package com.turf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.turf.enities.ContactUs;
import com.turf.enities.Customer;
import com.turf.service.ContactService;
import com.turf.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("title", "Home Page");
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title", "Register Here");
		return "register";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "Login Here");
		return "login";
	}

	@GetMapping("/grounds")
	public String grounds(Model model) {

		model.addAttribute("title", "View All Ground");
		return "grounds";
	}

	@GetMapping("/about")
	public String aboutUs(Model model) {

		model.addAttribute("title", "About Us");
		return "about";
	}

	@GetMapping("/contact")
	public String contactUs(Model model) {

		model.addAttribute("title", "Any Query Conatct Us");
		return "contact";
	}

	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute ContactUs contactUs, HttpSession session) {

		ContactUs saveContact = contactService.saveContact(contactUs);

		if (!ObjectUtils.isEmpty(saveContact)) {
			session.setAttribute("succMsg", "Query Saved Successfully");
		} else {
			session.setAttribute("succMsg", "Something Went Wrong!!!!!!");
		}

		return "redirect:/contact";
	}

	

	@GetMapping("/ground")
	public String viewground(Model model) {
		model.addAttribute("title", "Single Ground");
		return "viewground";
	}

	@GetMapping("/payment")
	public String payment(Model model) {
		model.addAttribute("title", "Make Payment");
		return "payment";
	}

	@PostMapping("/registerCustomer")
	public String saveCustomer(@ModelAttribute Customer customer, HttpSession session) {

		boolean existsByEmail = customerService.existsByEmail(customer.getEmail());

		if (existsByEmail) {
			session.setAttribute("errMsg", "Customer Already Exists");
		} else {

			Customer saveCustomer = customerService.saveCustomer(customer);

			if (!ObjectUtils.isEmpty(saveCustomer)) {
				session.setAttribute("succMsg", "Registration Successfully");
			} else {
				session.setAttribute("succMsg", "Something Went Wrong!!!!!!");
			}
		}

		return "redirect:/register";
	}

}
