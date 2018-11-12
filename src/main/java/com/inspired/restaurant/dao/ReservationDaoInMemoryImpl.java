package com.inspired.restaurant.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    public List<Reservation> loadReservations(Date from, Date to) {
	final List<Reservation> matchingList = new ArrayList<Reservation>();
	for (Reservation reservation : reservations) {
	    if (from == null || from.before(reservation.getTime()) ||
		    to == null || to.after(reservation.getTime())) {
		matchingList.add(reservation);
	    }
	}
	return matchingList;
    }

    @Override
    public void cancelReservation(Reservation toCancel) {
	final Iterator<Reservation> iter = reservations.iterator();
	boolean found = false;
	while (iter.hasNext() && !found) {
	    final Reservation reservation = iter.next();
	    if (reservation.equals(toCancel)) {
		iter.remove();
		found = true;
	    }
	}
    }

}
