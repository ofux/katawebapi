package com.sgcib.api.reservation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sgcib.api.room.RoomDTOAssembler;
import com.sgcib.model.reservation.ReservationEntity;

@Service
public class ReservationDTOAssembler {

    @Inject
    RoomDTOAssembler roomDTOAssembler;

    public ReservationDTO mapToDTO(final ReservationEntity entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setRoom(roomDTOAssembler.mapToDTO(entity.getRoom()));
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setDate(entity.getDate());

        addLinks(entity, dto);
        return dto;
    }

    private void addLinks(ReservationEntity entity, ReservationDTO dto) {
        dto.add(linkTo(methodOn(ReservationsApi.class).getOne(entity.getId())).withSelfRel());
    }

    public ReservationEntity mapToEntity(final ReservationDTO dto) {
        ReservationEntity entity = new ReservationEntity();
        entity.setId(dto.getReservationId());
        entity.setUserName(dto.getUserName());
        if (dto.getRoom() != null) {
            entity.setRoom(roomDTOAssembler.mapToEntity(dto.getRoom()));
        }
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setDate(dto.getDate());
        return entity;
    }
}
