package com.inspired.restaurant.dto;

import com.inspired.restaurant.constant.TableLocationEnu;

public class Table {

    private int tableNumber;
    private int capacity;
    private TableLocationEnu location;

    public Table() {
    }

    public Table(int tableNumber, int capacity, TableLocationEnu location) {
	this.tableNumber = tableNumber;
	this.capacity = capacity;
	this.location = location;
    }

    public int getTableNumber() {
	return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
	this.tableNumber = tableNumber;
    }

    public int getCapacity() {
	return capacity;
    }

    public void setCapacity(int capacity) {
	this.capacity = capacity;
    }

    public TableLocationEnu getLocation() {
	return location;
    }

    public void setLocation(TableLocationEnu location) {
	this.location = location;
    }
	
}
