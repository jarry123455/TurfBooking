package com.turf.service;

import java.util.List;

import com.turf.enities.Category;

public interface CategoryService {
	
	public Category saveCategory(Category category);
	
	public boolean existsByName(String name);
	
	public List<Category> getAllCategory();

}
