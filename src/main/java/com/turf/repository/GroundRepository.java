package com.turf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turf.enities.Ground;

public interface GroundRepository extends JpaRepository<Ground, Integer> {
	
	List<Ground> findByCategory(String category);
	
	
	

}
