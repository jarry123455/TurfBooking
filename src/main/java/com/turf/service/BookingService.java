package com.turf.service;


import java.sql.Date;
import java.util.List;

import com.turf.enities.Booking;
import com.turf.enities.Ground;

public interface BookingService {
	
	public Booking saveBooking(Booking booking);
	
	public boolean existsBySlotAndDateAndName(String slot,Date date,String name);
	
	
			 
}
