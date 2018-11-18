package com.inspired.restaurant.dao.filter;

import java.io.Serializable;

import com.inspired.restaurant.constant.TableLocationEnu;

public class ReservationFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private DateFilter time;
    private IntFilter partySize;
    private TableLocationEnu location;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public DateFilter getTime() {
	return time;
    }

    public void setTime(DateFilter time) {
	this.time = time;
    }

    public IntFilter getPartySize() {
	return partySize;
    }

    public void setPartySize(IntFilter partySize) {
	this.partySize = partySize;
    }

    public TableLocationEnu getLocation() {
	return location;
    }

    public void setLocation(TableLocationEnu location) {
	this.location = location;
    }
}
