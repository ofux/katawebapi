package com.sgcib.api.reservation;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.model.reservation.ReservationRepository;
import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReservationsApiIntegrationTest {

    @LocalServerPort
    int port;

    @Inject
    private ReservationRepository reservationRepo;

    @Inject
    private RoomRepository roomRepo;

    private RoomEntity room0;
    private RoomEntity room1;
    private ReservationEntity reservation;

    @Before
    @Transactional
    @Rollback
    public void setUp() {
        RestAssured.port = port;

        room0 = new RoomEntity();
        room0.setName("room0");
        room0 = roomRepo.save(room0);

        room1 = new RoomEntity();
        room1.setName("room1");
        room1 = roomRepo.save(room1);

        Date now = new Date();
        insertReservation(room0, now, "John", 10, 12);
        reservation = insertReservation(room0, now, "Bruce", 12, 13);
    }

    @Test
    public void testGetOne() {

        when().get("/v1/reservations/{id}", reservation.getId())//
                .then().body("user_name", is("Bruce")).body("id", is(reservation.getId().intValue()));
    }

    @Test
    public void testGetAll() {

        when().get("/v1/reservations")//
                .then().body("totalElements", is(2));
    }

    private ReservationEntity insertReservation(RoomEntity room, Date date, String userName, int startTime, int endTime) {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setRoom(room);
        reservation.setDate(date);
        reservation.setUserName(userName);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        return reservationRepo.save(reservation);
    }
}
