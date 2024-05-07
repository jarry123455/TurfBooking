package com.turf.repository;


import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turf.enities.Booking;

public interface BookingRepostory extends JpaRepository<Booking,Integer> {
	
	public boolean existsBySlotAndDate(String slot,Date date);
}
