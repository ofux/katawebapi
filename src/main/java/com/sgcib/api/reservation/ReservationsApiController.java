package com.sgcib.api.reservation;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgcib.api.room.RoomDTOAssembler;
import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.service.ReservationService;
import com.sgcib.service.RoomService;

@RestController
public class ReservationsApiController implements ReservationsApi {

    @Inject
    private RoomService roomService;

    @Inject
    private ReservationService reservationService;

    @Inject
    private RoomDTOAssembler roomDTOAssembler;

    @Inject
    private ReservationDTOAssembler reservationDTOAssembler;

    @Override
    public ResponseEntity<Page<ReservationDTO>> getAll(@RequestParam(value = "roomId", required = false) Long roomId, @RequestParam(value = "date", required = false) Date date,
            Pageable pageable) {
        Page<ReservationEntity> reservations = reservationService.getAll(roomId, date, pageable);
        Page<ReservationDTO> reservationsDTO = reservations.map(reservationDTOAssembler::mapToDTO);
        return new ResponseEntity<Page<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id) {
        ReservationEntity reservation = reservationService.getOne(id);
        ReservationDTO reservationDTO = reservationDTOAssembler.mapToDTO(reservation);
        return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
