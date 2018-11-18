package com.inspired.restaurant.dao.filter;

import java.io.Serializable;
import java.util.Date;

public class DateFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date from;
    private Date to;

    public DateFilter(Date exactDate) {
	this.from = exactDate;
	this.to = exactDate;
    }

    public DateFilter(Date from, Date to) {
	this.from = from;
	this.to = to;
    }

    public Date getFrom() {
	return from;
    }

    public void setFrom(Date from) {
	this.from = from;
    }

    public Date getTo() {
	return to;
    }

    public void setTo(Date to) {
	this.to = to;
    }

}
