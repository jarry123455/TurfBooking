package com.turf.service;


import java.sql.Date;

import com.turf.enities.Booking;

public interface BookingService {
	
	public Booking saveBooking(Booking booking);
	
	public boolean existsBySlotAndDate(String slot,Date date);
			 
}
