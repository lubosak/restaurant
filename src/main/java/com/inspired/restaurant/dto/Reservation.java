package com.inspired.restaurant.dto;

import java.util.Date;

import com.inspired.restaurant.constant.TableLocationEnu;

public class Reservation {

    private String name;
    private Date time;
    private int partySize;
    private Table table;
    private TableLocationEnu locationPreference;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getTime() {
	return time;
    }

    public void setTime(Date time) {
	this.time = time;
    }

    public int getPartySize() {
	return partySize;
    }

    public void setPartySize(int partySize) {
	this.partySize = partySize;
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
