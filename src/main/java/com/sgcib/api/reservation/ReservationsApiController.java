package com.sgcib.api.reservation;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationsApiController implements ReservationsApi {

    @Override
    public ResponseEntity<Page<ReservationDTO>> getAll(@RequestParam(value = "roomId", required = false) Long roomId, @RequestParam(value = "date", required = false) Date date,
            Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO resource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
