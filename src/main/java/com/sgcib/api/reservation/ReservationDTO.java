package com.sgcib.api.reservation;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sgcib.api.room.RoomDTO;

import io.swagger.annotations.ApiModelProperty;

public class ReservationDTO extends ResourceSupport {

    @JsonProperty(value = "id")
    @ApiModelProperty(notes = "Reservation ID")
    private Long id;

    @JsonProperty(value = "user_name", required = true)
    @ApiModelProperty(notes = "Name of the user who booked the room.")
    private String userName;

    @JsonProperty(value = "room")
    @ApiModelProperty(notes = "Booked room")
    private RoomDTO room;

    @JsonProperty(value = "date", required = true)
    @ApiModelProperty(notes = "Reservation day")
    private Date date;

    @JsonProperty(value = "time_start", required = true)
    @ApiModelProperty(notes = "Start time of reservation. This must be an integer between 0 and 23.")
    private Integer startTime;

    @JsonProperty(value = "time_end", required = true)
    @ApiModelProperty(notes = "End time of reservation. This must be an integer between 1 and 24 strictly greater than time_start.")
    private Integer endTime;

    @JsonProperty(value = "id")
    public Long getReservationId() {
        return id;
    }

    @JsonProperty(value = "id")
    public void setReservationId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

}
