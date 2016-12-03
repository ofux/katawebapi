package com.sgcib.api.reservation;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/v1/reservations")
@Api(value = "/v1/reservations", consumes = "application/json", produces = "application/json")
public interface ReservationsApi {

    @ApiOperation(value = "Gets `Reservation` objects.", notes = "Result is paginated", response = Page.class, responseContainer = "List", tags = { "Reservations" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = Page.class) })
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<ReservationDTO>> getAll(//
            @ApiParam(value = "Room ID", required = false) //
            @RequestParam(value = "roomId", required = false) //
            Long roomId, //
            @ApiParam(value = "Reservation day", required = false) //
            @RequestParam(value = "date", required = false) //
            Date date, //
            Pageable pageable);

    @ApiOperation(value = "Gets `Reservation` object.", response = ReservationDTO.class, tags = { "Reservations" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = ReservationDTO.class) })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ReservationDTO> getOne(@PathVariable("id") Long id);

    @ApiOperation(value = "Creates `Reservation` object.", notes = "", response = ReservationDTO.class, tags = { "Reservations" })
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation created", response = ReservationDTO.class) })
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO resource);

    @ApiOperation(value = "Deletes `Reservation` object.", notes = "", response = ReservationDTO.class, tags = { "Reservations" })
    @ApiResponses(value = { @ApiResponse(code = 202, message = "Reservation deleted") })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
