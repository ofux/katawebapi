package com.sgcib.api.room;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgcib.api.reservation.ReservationDTO;
import com.sgcib.api.reservation.ReservationDTOAssembler;
import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.model.room.RoomEntity;
import com.sgcib.service.ReservationConfictException;
import com.sgcib.service.ReservationService;
import com.sgcib.service.RoomService;

@RestController
public class RoomsApiController implements RoomsApi {

    @Inject
    private RoomService roomService;

    @Inject
    private ReservationService reservationService;

    @Inject
    private RoomDTOAssembler roomDTOAssembler;

    @Inject
    private ReservationDTOAssembler reservationDTOAssembler;

    @Override
    public ResponseEntity<Page<RoomDTO>> getAll(Pageable pageable) {
        Page<RoomEntity> rooms = roomService.getAll(pageable);
        Page<RoomDTO> roomsDTO = rooms.map(roomDTOAssembler::mapToDTO);
        return new ResponseEntity<Page<RoomDTO>>(roomsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoomDTO> getOne(@PathVariable("id") Long id) {
        RoomEntity room = roomService.getOne(id);
        RoomDTO roomDTO = roomDTOAssembler.mapToDTO(room);
        return new ResponseEntity<RoomDTO>(roomDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ReservationDTO>> getAllReservations(@PathVariable("id") Long roomId, @RequestParam(value = "date", required = false) Date date, Pageable pageable) {
        Page<ReservationEntity> reservations = reservationService.getAll(roomId, date, pageable);
        Page<ReservationDTO> reservationsDTO = reservations.map(reservationDTOAssembler::mapToDTO);
        return new ResponseEntity<Page<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReservationDTO> createReservation(@RequestParam(value = "roomId", required = true) Long roomId, @RequestBody ReservationDTO resource)
            throws ReservationConfictException {
        ReservationEntity reservation = reservationDTOAssembler.mapToEntity(resource);
        reservation = reservationService.create(reservation, roomId);
        ReservationDTO reservationDTO = reservationDTOAssembler.mapToDTO(reservation);
        return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.CREATED);
    }
}
