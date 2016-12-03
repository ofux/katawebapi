package com.sgcib.api.room;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sgcib.api.reservation.ReservationDTO;
import com.sgcib.api.reservation.ReservationDTOAssembler;
import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.service.ReservationConfictException;
import com.sgcib.service.ReservationService;

@ControllerAdvice
public class ReservationConfictExceptionHandler extends ResponseEntityExceptionHandler {

    @Inject
    private ReservationService reservationService;

    @Inject
    private ReservationDTOAssembler reservationDTOAssembler;

    @ExceptionHandler({ ReservationConfictException.class })
    protected ResponseEntity<List<ReservationDTO>> handleInvalidRequest(Exception e, WebRequest request) {
        ReservationConfictException rce = (ReservationConfictException) e;
        Page<ReservationEntity> reservations = reservationService.getAll(rce.getRoomId(), rce.getDate(), null);
        Page<ReservationDTO> reservationsDTO = reservations.map(reservationDTOAssembler::mapToDTO);
        return new ResponseEntity<List<ReservationDTO>>(reservationsDTO.getContent(), HttpStatus.CONFLICT);
    }
}
