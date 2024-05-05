package com.turf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.turf.enities.Category;
import com.turf.enities.Ground;
import com.turf.service.CategoryService;
import com.turf.service.GroundService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private GroundService groundService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String admin() {

		return "admin/index";
	}

	@GetMapping("/addground")
	public String addground(Model model) {

		List<Category> allCategory = categoryService.getAllCategory();
		model.addAttribute("allcategory", allCategory);
		model.addAttribute("title", "Add Ground");
		return "admin/addground";
	}
	
	@GetMapping("/editGround/{id}")
	public String editGround(@PathVariable int id,Model model) {
		
		model.addAttribute("ground",groundService.getGroundById(id));
		List<Category> allCategory =categoryService.getAllCategory();
		model.addAttribute("allCategory", allCategory);
		return "admin/editGround";
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
	
	@PostMapping("/updateGround")
	public String updateGround(@ModelAttribute Ground ground,@RequestParam("file") MultipartFile file
	,HttpSession session) {
		
		Ground updateGround = groundService.updateGround(ground, file);
		
		if (!ObjectUtils.isArray(updateGround)) {
			session.setAttribute("succMsg", "Ground Update Successfully");
		}else {
			session.setAttribute("errMsg", "Something Went Wrong!");
		}
		
		return "redirect:/admin/editGround/" +ground.getId();
	}

	@GetMapping("/deleteGround/{id}")
	public String deleteGround(@PathVariable int id,HttpSession session) {
		
		boolean deleteGround = groundService.deleteGround(id);
		
		if (deleteGround) {
			session.setAttribute("succMsg", "Ground Deleted Successfully");
		}else {
			session.setAttribute("succMsg", "Something Went Wrong!");
		}
		return "redirect:/admin/viewground";
		
	}
	@GetMapping("/viewground")
	public String viewground(Model model) {

		model.addAttribute("grounds", groundService.getAllGround());
		
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
	public String addandviewcategory(Model model) {

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("title","Add And View Category");
		return "admin/addandviewcategory";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
			HttpSession session) throws IOException {
		String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
		category.setImageName(imageName);

		Boolean existCategory = categoryService.existsByName(category.getName());

		if (existCategory) {

			session.setAttribute("errMsg", "Category Name Already exits");
		} else {
			Category saveCategory = categoryService.saveCategory(category);

			if (ObjectUtils.isEmpty(saveCategory)) {
				session.setAttribute("errMsg", "Not Saved ! Internal Server Error");
			} else {

				String uploadDir = "static/img/category_img";

				// Create directory if it does not exist
				File directory = new File(uploadDir);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				Path path = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
				System.out.println(path);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				session.setAttribute("succMsg", "Saved Successfully");
			}
		}
		return "redirect:/admin/addandviewcategory";
	}

}
