package com.turf.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turf.enities.Ground;
import com.turf.repository.GroundRepository;
import com.turf.service.GroundService;


@Service
public class GroundServiceImpl implements GroundService {
	
	@Autowired
	private GroundRepository groundRepository;

	@Override
	public Ground saveGround(Ground ground) {
		
		return groundRepository.save(ground);
	}

}
