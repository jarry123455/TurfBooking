package com.turf.repository;


import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turf.enities.Booking;

public interface BookingRepostory extends JpaRepository<Booking,Integer> {
	
	public boolean existsBySlotAndDate(String slot,Date date);
	
	List<Booking> findByName(String name);

	
}
