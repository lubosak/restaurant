package com.inspired.restaurant.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inspired.restaurant.dao.filter.DateFilter;
import com.inspired.restaurant.dao.filter.IntFilter;
import com.inspired.restaurant.dao.filter.ReservationFilter;
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
    public List<Reservation> listReservations(ReservationFilter filter) {
	List<Reservation> output = new ArrayList<>();
	for (Reservation reservation : reservations) {
	    if (filter == null) {
		output.add(reservation);
		continue;
	    }
	    if (matchesFilter(reservation.getName(), filter.getName())
		    && matchesFilter(reservation.getTime(), filter.getTime())
		    && matchesFilter(reservation.getPartySize(), filter.getPartySize())
		    && matchesFilter(reservation.getLocationPreference(), filter.getLocation())) {
		output.add(reservation);
	    }
	}
	return output;
    }

    private <T> boolean matchesFilter(T value, T filter) {
	if (filter == null) {
	    return true;
	}
	return filter.equals(value);
    }

    private <T extends Comparable<T>> boolean matchesFilter(T value, T from, T to) {
	return (from == null || from.compareTo(value) <= 0) && (to == null || to.compareTo(value) >= 0);
    }

    private boolean matchesFilter(Date value, DateFilter filter) {
	if (filter == null) {
	    return true;
	}
	return matchesFilter(value, filter.getFrom(), filter.getTo());
    }

    private boolean matchesFilter(Integer value, IntFilter filter) {
	if (filter == null) {
	    return true;
	}
	return matchesFilter(value, filter.getFrom(), filter.getTo());
    }

    @Override
    public void deleteReservation(Reservation toCancel) {
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
