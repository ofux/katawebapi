package com.sgcib.service;

import java.util.Date;

public class ReservationConfictException extends Exception {

    private static final long serialVersionUID = 2877807025398458585L;

    private final Long roomId;

    private final Date date;

    public ReservationConfictException(Long roomId, Date date) {
        super();
        this.roomId = roomId;
        this.date = date;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Date getDate() {
        return date;
    }

}
