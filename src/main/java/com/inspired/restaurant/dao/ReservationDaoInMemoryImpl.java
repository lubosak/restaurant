package com.inspired.restaurant.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.inspired.restaurant.dto.Reservation;

@Service
public class ReservationDaoInMemoryImpl implements ReservationDao {

    private volatile List<Reservation> reservations;

    public ReservationDaoInMemoryImpl() {
	reservations = new ArrayList<Reservation>();
    }

    @Override
    public void addReservation(Reservation reservation) {
	reservations.add(reservation);
    }

    @Override
    public List<Reservation> loadReservations(String customerName) {
	final List<Reservation> matchingList = new ArrayList<Reservation>();
	for (Reservation reservation : reservations) {
	    if (reservation.getName().equals(customerName)) {
		matchingList.add(reservation);
	    }
	}
	return matchingList;
    }

    @Override
    public List<Reservation> loadReservations(Date reservationTime) {
	final List<Reservation> matchingList = new ArrayList<Reservation>();
	for (Reservation reservation : reservations) {
	    if (reservation.getReservationTime().equals(reservationTime)) {
		matchingList.add(reservation);
	    }
	}
	return matchingList;
    }

    @Override
    public Map<Date, List<Reservation>> reservationsByDate(Date from, Date to) {
	final Map<Date, List<Reservation>> result = new HashMap<Date, List<Reservation>>();
	for (Reservation reservation : reservations) {
	    final Date reservationTime = reservation.getReservationTime();
	    if ((from == null || from.before(reservationTime)) && to == null || to.after(reservationTime)) {
		List<Reservation> listForTime = result.get(reservationTime);
		if (listForTime == null) {
		    listForTime = new ArrayList<Reservation>();
		    result.put(reservationTime, listForTime);
		}
		listForTime.add(reservation);
	    }
	}
	return result;
    }

}
