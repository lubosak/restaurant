package com.inspired.restaurant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dao.ReservationDao;
import com.inspired.restaurant.dao.TableDao;
import com.inspired.restaurant.dto.Reservation;
import com.inspired.restaurant.dto.Table;
import com.inspired.restaurant.service.ReservationManager;

@Service
public class ReservationManagerImpl implements ReservationManager {
    
    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TableDao tableDao;

    @Override
    public List<Date> getAvailability(int partySize, Date from, Date to, TableLocationEnu tablePref) {
	final List<Date> availableTimes = new ArrayList<Date>();

	for (Date timeSlot : getReservationTimeSlots(from, to)) {
	    if (getAvailableTable(timeSlot, partySize, tablePref) != null) {
		availableTimes.add(timeSlot);
	    }
	}

	return availableTimes;
    }

    @Override
    public Reservation createReservation(String name, int partySize, Date time, TableLocationEnu tablePref) {
	final Table table = getAvailableTable(time, partySize, tablePref);
	if (table == null) {
	    return null;
	}
	final Reservation reservation = new Reservation();
	reservation.setName(name);
	reservation.setPartySize(partySize);
	reservation.setLocationPreference(tablePref);
	reservation.setTable(table);
	reservationDao.addReservation(reservation);
	return reservation;
    }

    private Table getAvailableTable(Date time, int partySize, TableLocationEnu tablePref) {
	final List<Table> tables = tableDao.loadTables(tablePref);
	final Set<Table> occupiedTables = getOccupiedTables(time);

	for (Table table : tables) {
	    if (table.getCapacity() < partySize) {
		continue;
	    }
	    if (occupiedTables.contains(table)) {
		continue;
	    }
	    return table;
	}
	return null;
    }
    
    private Set<Table> getOccupiedTables(Date time) {
	final Calendar from = Calendar.getInstance();
	from.setTime(time);
	from.add(Calendar.MINUTE, -90);
	final Calendar to = Calendar.getInstance();
	to.setTime(time);
	to.add(Calendar.MINUTE, 90);

	final Map<Date, List<Reservation>> reservationMap = reservationDao.reservationsByDate(from.getTime(),
		to.getTime());

	final Set<Table> occupiedTables = new HashSet<Table>();
	for (List<Reservation> reservations : reservationMap.values()) {
	    for (Reservation reservation : reservations) {
		occupiedTables.add(reservation.getTable());
	    }
	}
	return occupiedTables;
    }

    private boolean isTableOccupied(Table table, Date time) {
	final Calendar from = Calendar.getInstance();
	from.setTime(time);
	from.add(Calendar.MINUTE, -90);
	final Calendar to = Calendar.getInstance();
	to.setTime(time);
	to.add(Calendar.MINUTE, 90);
	final Map<Date, List<Reservation>> reservationMap = 
		reservationDao.reservationsByDate(from.getTime(), to.getTime());

	for (List<Reservation> reservations : reservationMap.values()) {
	    for (Reservation reservation : reservations) {
		if (table.equals(reservation.getTable())) {
		    return true;
		}
	    }
	}
	return false;
    }

    private List<Date> getReservationTimeSlots(Date from, Date to) {
	Calendar timeSlot = getNextTimeSlot(from);

	final List<Date> timeSlots = new ArrayList<Date>();
	while (timeSlot.getTime().before(to)) {
	    timeSlots.add(new Date(timeSlot.getTimeInMillis()));
	    timeSlot.add(Calendar.MINUTE, 15);
	}

	return timeSlots;
    }

    private Calendar getNextTimeSlot(Date from) {
	Calendar timeSlot = Calendar.getInstance();
	timeSlot.setTime(from);
	timeSlot.set(Calendar.MILLISECOND, 0);
	timeSlot.set(Calendar.SECOND, 0);
	if (timeSlot.get(Calendar.HOUR_OF_DAY) >= 22) {
	    timeSlot.add(Calendar.HOUR_OF_DAY, 2);
	}
	if (timeSlot.get(Calendar.HOUR_OF_DAY) < 17) {
	    timeSlot.set(Calendar.HOUR_OF_DAY, 17);
	    timeSlot.set(Calendar.MINUTE, 0);
	}
	if (timeSlot.get(Calendar.MINUTE) < 15) {
	    timeSlot.set(Calendar.MINUTE, 15);
	} else if (timeSlot.get(Calendar.MINUTE) < 30) {
	    timeSlot.set(Calendar.MINUTE, 30);
	} else if (timeSlot.get(Calendar.MINUTE) < 45) {
	    timeSlot.set(Calendar.MINUTE, 45);
	} else {
	    timeSlot.set(Calendar.MINUTE, 0);
	    timeSlot.add(Calendar.HOUR_OF_DAY, 1);
	}
	return timeSlot;
    }

}
