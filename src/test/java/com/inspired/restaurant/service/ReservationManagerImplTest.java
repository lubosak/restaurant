package com.inspired.restaurant.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.util.Assert;

import com.inspired.restaurant.constant.TableLocationEnu;
import com.inspired.restaurant.dao.ReservationDaoInMemoryImpl;
import com.inspired.restaurant.dao.TableDaoInMemoryImpl;
import com.inspired.restaurant.dao.TableDaoStaticImpl;
import com.inspired.restaurant.dto.Reservation;
import com.inspired.restaurant.dto.Table;

public class ReservationManagerImplTest {

    @Test
    public void testCreateReservation_reservationHasCorrectData() {
	// setup
	String name = "test";
	int partySize = 4;
	Date time = new Date();
	TableLocationEnu tablePref = TableLocationEnu.INSIDE;
	ReservationManager reservationManager =
		new ReservationManagerImpl(new ReservationDaoInMemoryImpl(), new TableDaoStaticImpl());

	// perform
	Reservation reservation = reservationManager.createReservation(name, partySize, time, tablePref);

	// check
	assertEquals("name is incorrect", name, reservation.getName());
	assertEquals("partySize is incorrect", partySize, reservation.getPartySize());
	assertEquals("time is incorrect", time, reservation.getTime());
	assertEquals("table preference is incorrect", tablePref, reservation.getLocationPreference());
    }
    
    // nullTimeThrowsException
    // nullNameThrowsException
    // partySizeTooLarge
    // partySizeZero
    // nullTablePrefIsOk
    // timeInThePast is rejected

    @Test
    public void testCreateReservation_noAvailableTable_returnsNullReservation() {
	int tableCapacity = 2;
	int partySize = 4;
	TableDaoInMemoryImpl tableDao = new TableDaoInMemoryImpl();
	tableDao.addTable(new Table(1, tableCapacity, TableLocationEnu.INSIDE));
	ReservationManager reservationManager = new ReservationManagerImpl(new ReservationDaoInMemoryImpl(), tableDao);

	Reservation reservation = 
		reservationManager.createReservation("test", partySize, new Date(), TableLocationEnu.INSIDE);

	Assert.isNull(reservation, "no table should be available to make reservation");
    }

    @Test
    public void testCreateReservation_tableIsAvailable_returnsTableInReservation() {
	int tableNumber = 1;
	int tableCapacity = 4;
	int partySize = 4;
	TableDaoInMemoryImpl tableDao = new TableDaoInMemoryImpl();
	tableDao.addTable(new Table(tableNumber, tableCapacity, TableLocationEnu.INSIDE));
	ReservationManager reservationManager = new ReservationManagerImpl(new ReservationDaoInMemoryImpl(), tableDao);

	Reservation reservation = 
		reservationManager.createReservation("test", partySize, new Date(), TableLocationEnu.INSIDE);

	Assert.notNull(reservation, "With table available, reservation should be returned");
	assertEquals("table number should match", tableNumber, reservation.getTable().getTableNumber());
    }

    // reservesSmallestAvailableTable (new feature)
}
