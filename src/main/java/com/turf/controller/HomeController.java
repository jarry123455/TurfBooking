package com.turf.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.turf.enities.Booking;
import com.turf.enities.Category;
import com.turf.enities.ContactUs;
import com.turf.enities.Customer;
import com.turf.enities.Ground;
import com.turf.service.BookingService;
import com.turf.service.CategoryService;
import com.turf.service.ContactService;
import com.turf.service.CustomerService;
import com.turf.service.GroundService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private GroundService groundService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BookingService bookingService;

	@GetMapping("/booking")
	public String payment(Model model) {
		model.addAttribute("title", "Make Payment");
		return "booking";
	}

	@PostMapping("/saveBooking")
	public String saveBooking(@ModelAttribute Booking booking, HttpSession session) {

		boolean existsByStartTimeAndEndTime = bookingService.existsByStartTimeAndEndTime(booking.getStartTime(),
				booking.getEndTime());

		if (existsByStartTimeAndEndTime) {

			session.setAttribute("errMsg", "Slot Booked Already!!!!");
		} else {

			Booking saveBooking = bookingService.saveBooking(booking);

			if (!ObjectUtils.isEmpty(saveBooking)) {
				session.setAttribute("succMsg", "Booking Saved Successfully");
			} else {
				session.setAttribute("succMsg", "Someting Went Wrong");
			}
		}
		return "redirect:/booking";

	}

	@GetMapping("/grounds")
	public String grounds(Model model, @RequestParam(value = "category", defaultValue = "") String category) {

		List<Category> allActiveCategory = categoryService.getAllActiveCategory();
		List<Ground> allActiveGrounds = groundService.getAllActiveGrounds(category);
		model.addAttribute("categories", allActiveCategory);
		model.addAttribute("grounds", allActiveGrounds);
		model.addAttribute("paramValue", category);
		model.addAttribute("title", "View All Ground");
		return "grounds";
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("categories", categoryService.getAllActiveCategory());
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

	@GetMapping("/ground/{id}")
	public String viewground(@PathVariable int id, Model model) {
		boolean isLoggedIn = true; // or false based on your actual logic
		model.addAttribute("isLoggedIn", isLoggedIn);
		Ground groundById = groundService.getGroundById(id);
		model.addAttribute("g", groundById);
		model.addAttribute("title", "Single Ground");
		return "viewground";
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
