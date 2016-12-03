package com.sgcib.api.room;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class RoomDTO extends ResourceSupport {

    @JsonProperty(value = "id", required = true)
    @ApiModelProperty(notes = "Room ID")
    private Long id;

    @JsonProperty(value = "name", required = true)
    @ApiModelProperty(notes = "Room Name")
    private String name;

    @JsonProperty(value = "id", required = true)
    public Long getRoomId() {
        return id;
    }

    @JsonProperty(value = "id", required = true)
    public void setRoomId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
