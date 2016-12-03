package com.sgcib.api.room;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sgcib.api.reservation.ReservationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/v1/rooms")
@Api(value = "/v1/rooms", consumes = "application/json", produces = "application/json")
public interface RoomsApi {

    @ApiOperation(value = "Gets `Room` objects.", notes = "Result is paginated", response = RoomDTO.class, responseContainer = "List", tags = { "Rooms" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = RoomDTO.class) })
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity<Page<RoomDTO>> getAll(Pageable pageable);

    @ApiOperation(value = "Gets `Room` object.", response = RoomDTO.class, tags = { "Rooms" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = RoomDTO.class) })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<RoomDTO> getOne(@PathVariable("id") Long id);

    @ApiOperation(value = "Gets `Reservation` objects of the room.", notes = "Result is paginated", response = ReservationDTO.class, responseContainer = "List", tags = { "Rooms" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = ReservationDTO.class) })
    @RequestMapping(value = "/{id}/reservations", method = RequestMethod.GET)
    ResponseEntity<Page<ReservationDTO>> getAllReservations(@PathVariable("id") Long roomId, //
            @ApiParam(value = "Reservation day", required = false) //
            @RequestParam(value = "date", required = false) //
            Date date, //
            Pageable pageable);

    @ApiOperation(value = "Creates `Reservation` object for the room.", notes = "", response = ReservationDTO.class, tags = { "Rooms" })
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation created", response = ReservationDTO.class) })
    @RequestMapping(value = "/{id}/reservations", method = RequestMethod.POST)
    ResponseEntity<ReservationDTO> createReservation(@PathVariable(value = "id", required = true) Long roomId, @RequestBody ReservationDTO resource);

}
