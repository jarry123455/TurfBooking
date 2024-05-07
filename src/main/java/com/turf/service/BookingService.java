package com.turf.service;

import java.time.LocalDateTime;

import com.turf.enities.Booking;

public interface BookingService {
	
	public Booking saveBooking(Booking booking);
	
	public boolean existsByStartTimeAndEndTime(LocalDateTime startTime,LocalDateTime endTime);

}
