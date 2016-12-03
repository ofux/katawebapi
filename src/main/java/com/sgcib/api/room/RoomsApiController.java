package com.sgcib.api.room;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgcib.api.reservation.ReservationDTO;

@RestController
public class RoomsApiController implements RoomsApi {

    @Override
    public ResponseEntity<Page<RoomDTO>> getAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<RoomDTO> getOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Page<ReservationDTO>> getAllReservations(@RequestParam(value = "roomId", required = false) Long roomId,
            @RequestParam(value = "date", required = false) Date date, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> createReservation(@RequestParam(value = "roomId", required = true) Long roomId, @RequestBody ReservationDTO resource) {
        // TODO Auto-generated method stub
        return null;
    }

}
