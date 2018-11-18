package com.inspired.restaurant.dao;

import java.util.List;

import com.inspired.restaurant.dao.filter.ReservationFilter;
import com.inspired.restaurant.dto.Reservation;

public interface ReservationDao {

    public void addReservation(Reservation reservation);

    public List<Reservation> listReservations(ReservationFilter filter);

    public void deleteReservation(Reservation reservation);
}
