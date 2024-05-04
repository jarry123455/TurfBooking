package com.turf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.turf.enities.Ground;
import com.turf.service.GroundService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GroundService groundService;

	@GetMapping("/")
	public String admin() {

		return "admin/index";
	}

	@GetMapping("/addground")
	public String addground() {

		return "admin/addground";
	}

	@PostMapping("/saveGround")
	public String saveGround(@ModelAttribute Ground ground, @RequestParam("file") MultipartFile image,
			HttpSession session) throws IOException {

		String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();

		ground.setImage(imageName);

		Ground saveProduct = groundService.saveGround(ground);

		if (!ObjectUtils.isEmpty(saveProduct)) {

			if (!image.isEmpty()) {

				String uploadDir = "static/img/ground_img";

				// Create directory if it does not exist
				File directory = new File(uploadDir);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				Path path = Paths.get(uploadDir + File.separator + image.getOriginalFilename());
				System.out.println(path);
				Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			session.setAttribute("succMsg", "Ground Saved Successfully");
		} else {
			session.setAttribute("errMsg", "Internal Server Error!");
		}

		return "redirect:/admin/addground";

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
