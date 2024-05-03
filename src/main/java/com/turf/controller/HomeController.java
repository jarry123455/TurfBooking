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
		
		model.addAttribute("title","View All Ground");
		return "grounds";
	}
	@GetMapping("/about")
	public String aboutUs() {
		
		return "about";
	}
	@GetMapping("/contact")
	public String contactUs() {
		
		return "contact";
	}
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	@GetMapping("/viewground")
	public String viewground() {
		
		return "viewground";
	}

}
