package com.inspired.restaurant.dto;

import java.util.Date;

import com.inspired.restaurant.constant.TableLocationEnu;

public class Reservation {

    private String name;
    private int partySize;
    private Date reservationTime;
    private Table table;
    private TableLocationEnu locationPreference;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getPartySize() {
	return partySize;
    }

    public void setPartySize(int partySize) {
	this.partySize = partySize;
    }

    public Date getReservationTime() {
	return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
	this.reservationTime = reservationTime;
    }

    public Table getTable() {
	return table;
    }

    public void setTable(Table table) {
	this.table = table;
    }

    public TableLocationEnu getLocationPreference() {
	return locationPreference;
    }

    public void setLocationPreference(TableLocationEnu locationPreference) {
	this.locationPreference = locationPreference;
    }

}
