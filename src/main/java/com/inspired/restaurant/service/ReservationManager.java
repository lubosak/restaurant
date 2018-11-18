package com.inspired.restaurant.service;

import java.util.Date;
import java.util.List;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dto.Reservation;

public interface ReservationManager {

    public List<Date> getAvailability(int partySize, Date from, Date to, TableLocationEnu tablePref);

    public Reservation createReservation(String name, int partySize, Date time, TableLocationEnu tablePref);

    public Reservation retrieveReservation(String name, Date time);

    public void cancelReservation(String name, Date time);

}
