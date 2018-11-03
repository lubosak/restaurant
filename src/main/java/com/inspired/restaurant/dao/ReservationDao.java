package com.inspired.restaurant.dao;

import java.util.Date;
import java.util.List;

import com.inspired.restaurant.dto.Reservation;

public interface ReservationDao {

    public void addReservation(Reservation reservation);

    public List<Reservation> loadReservations(String customerName);

    public List<Reservation> loadReservations(Date from, Date to);

    public void cancelReservation(Reservation reservation);
}
