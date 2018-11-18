package com.inspired.restaurant.dao.filter;

import java.io.Serializable;

public class IntFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer from;
    private Integer to;

    public IntFilter(Integer exactValue) {
	this.from = exactValue;
	this.to = exactValue;
    }

    public IntFilter(Integer from, Integer to) {
	this.from = from;
	this.to = to;
    }

    public Integer getFrom() {
	return from;
    }

    public void setFrom(Integer from) {
	this.from = from;
    }

    public Integer getTo() {
	return to;
    }

    public void setTo(Integer to) {
	this.to = to;
    }

}
