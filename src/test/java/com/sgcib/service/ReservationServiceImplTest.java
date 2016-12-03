package com.sgcib.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.model.reservation.ReservationRepository;
import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ReservationServiceImplTest {

    @Inject
    private ReservationRepository reservationRepo;

    @Inject
    private RoomRepository roomRepo;

    @Inject
    private ReservationService service;

    private RoomEntity room0;
    private RoomEntity room1;

    @Before
    public void setUp() {
        room0 = new RoomEntity();
        room0.setName("room0");
        room0 = roomRepo.save(room0);

        room1 = new RoomEntity();
        room1.setName("room1");
        room1 = roomRepo.save(room1);
    }

    @Test
    public void testGetAll() throws Exception {
        // Arrange
        Date now = new Date();
        insertReservation(room0, now, "John", 10, 12);
        insertReservation(room0, now, "Bruce", 12, 13);

        // Act
        Page<ReservationEntity> result = service.getAll(null, null, null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getRoom().getName()).isEqualTo("room0");
        assertThat(result.getContent().get(1).getRoom().getName()).isEqualTo("room0");
    }

    @Test
    public void testGetAllWithRoomId() throws Exception {
        // Arrange
        Date now = new Date();
        insertReservation(room0, now, "John", 10, 12);
        insertReservation(room1, now, "Bob", 8, 15);

        // Act
        Page<ReservationEntity> result = service.getAll(room1.getId(), null, null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getUserName()).isEqualTo("Bob");
        assertThat(result.getContent().get(0).getRoom().getName()).isEqualTo("room1");
    }

    @Test
    public void testGetAllWithDate() throws Exception {
        // Arrange
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        insertReservation(room0, today, "John", 10, 12);
        insertReservation(room0, tomorrow, "Bob", 8, 15);

        // Act
        Page<ReservationEntity> result = service.getAll(null, today, null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getUserName()).isEqualTo("John");
    }

    @Test
    public void testGetAllWithRoomIdAndDate() throws Exception {
        // Arrange
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        insertReservation(room0, today, "John", 10, 12);
        insertReservation(room1, today, "Bob", 8, 15);
        insertReservation(room1, tomorrow, "Bruce", 12, 15);

        // Act
        Page<ReservationEntity> result = service.getAll(room1.getId(), today, null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getUserName()).isEqualTo("Bob");
        assertThat(result.getContent().get(0).getRoom().getName()).isEqualTo("room1");
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        // Arrange

        // Act
        Page<ReservationEntity> result = service.getAll(null, null, null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    public void testGetOne() throws Exception {
        // Arrange
        Date now = new Date();
        ReservationEntity reservation = insertReservation(room0, now, "John", 10, 12);

        // Act
        ReservationEntity result = service.getOne(reservation.getId());

        // Assert
        assertThat(result.getId()).isEqualTo(reservation.getId());
    }

    @Test
    public void testGetOneNotFound() throws Exception {
        // Arrange

        // Act
        ReservationEntity result = service.getOne(1L);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    public void testCreate() throws Exception {
        // Arrange
        Date now = new Date();

        ReservationEntity reservation = new ReservationEntity();
        reservation.setDate(now);
        reservation.setUserName("John");
        reservation.setStartTime(10);
        reservation.setEndTime(12);

        // Act
        ReservationEntity result = service.create(reservation, room0.getId());

        // Assert
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRoom().getId()).isEqualTo(room0.getId());
        assertThat(result.getDate()).isInSameDayAs(now);
        assertThat(result.getUserName()).isEqualTo("John");
        assertThat(result.getStartTime()).isEqualTo(10);
        assertThat(result.getEndTime()).isEqualTo(12);
    }

    @Test
    public void testDelete() throws Exception {
        // Arrange
        Date now = new Date();
        ReservationEntity reservation = insertReservation(room0, now, "John", 10, 12);

        // Act
        service.delete(reservation.getId());

        // Assert
        assertThat(reservationRepo.findOne(reservation.getId())).isNull();
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
