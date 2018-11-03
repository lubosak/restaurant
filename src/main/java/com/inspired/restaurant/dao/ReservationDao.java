package com.inspired.restaurant.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inspired.restaurant.dto.Reservation;

public interface ReservationDao {

    public void addReservation(Reservation reservation);

    public List<Reservation> loadReservations(String customerName);

    public List<Reservation> loadReservations(Date reservationTime);

    public Map<Date, List<Reservation>> reservationsByDate(Date from, Date to);
}
