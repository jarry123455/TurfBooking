package com.turf.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turf.enities.Booking;

public interface BookingRepostory extends JpaRepository<Booking,Integer> {
	
	public boolean existsByStartTimeAndEndTime(LocalDateTime startTime,LocalDateTime endTime);

}
