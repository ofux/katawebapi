package com.sgcib.api.room;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import com.sgcib.model.room.RoomEntity;

@Service
public class RoomDTOAssembler {

    public RoomDTO mapToDTO(final RoomEntity entity) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(entity.getId());
        dto.setName(entity.getName());
        addLinks(entity, dto);
        return dto;
    }

    private void addLinks(RoomEntity entity, RoomDTO dto) {
        dto.add(linkTo(methodOn(RoomsApi.class).getOne(entity.getId())).withSelfRel());
        dto.add(linkTo(methodOn(RoomsApi.class).getAllReservations(entity.getId(), null, null)).withRel("reservations"));
    }

    public RoomEntity mapToEntity(final RoomDTO dto) {
        RoomEntity entity = new RoomEntity();
        entity.setId(dto.getRoomId());
        entity.setName(dto.getName());
        return entity;
    }
}
