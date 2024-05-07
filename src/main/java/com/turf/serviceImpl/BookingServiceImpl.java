package com.turf.serviceImpl;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.turf.enities.Booking;
import com.turf.repository.BookingRepostory;
import com.turf.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepostory bookingRepostory;

	@Override
	public Booking saveBooking(Booking booking) {

		return bookingRepostory.save(booking);
	}

	@Override
	public boolean existsBySlotAndDate(String slot,Date date) {
		
		return bookingRepostory.existsBySlotAndDate(slot,date);
	}

	

	

}
