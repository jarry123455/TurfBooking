package com.turf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String admin() {
		
		return "admin/index";
	}
	
	@GetMapping("/addground")
	public String addground() {
		
		return "admin/addground";
	}
	
	@GetMapping("/viewground")
	public String viewground() {
		
		return "admin/viewground";
	}
	
	@GetMapping("/booking")
	public String booking() {
		
		return "admin/booking";
	}
	@GetMapping("/allcustomer")
	public String allcustomer() {
		
		return "admin/allcustomer";
	}
	@GetMapping("/review")
	public String review() {
		
		return "admin/review";
	}
	
	@GetMapping("/addandviewcategory")
	public String addandviewcategory() {
		
		return "admin/addandviewcategory";
	}
	
	

}
