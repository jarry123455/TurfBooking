package com.turf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("title", "Home Page");
		return "index";
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

}
