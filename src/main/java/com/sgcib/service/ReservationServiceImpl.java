package com.sgcib.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sgcib.model.reservation.ReservationEntity;
import com.sgcib.model.reservation.ReservationRepository;
import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Inject
    private ReservationRepository reservationRepo;

    @Inject
    private RoomRepository roomRepo;

    @Override
    public Page<ReservationEntity> getAll(Long roomId, Date date, Pageable pageable) {
        if (roomId != null) {
            if (date != null) {
                return reservationRepo.findAllByRoomIdAndDate(roomId, date, pageable);
            }
            return reservationRepo.findAllByRoomId(roomId, pageable);
        }
        if (date != null) {
            return reservationRepo.findAllByDate(date, pageable);
        }
        return reservationRepo.findAll(pageable);
    }

    @Override
    public ReservationEntity getOne(Long id) {
        return reservationRepo.findOne(id);
    }

    @Override
    public ReservationEntity create(ReservationEntity reservation, Long roomId) throws ReservationConfictException {
        RoomEntity room = roomRepo.findOne(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " could not be found.");
        }
        if (isInConfict(reservation, roomId)) {
            throw new ReservationConfictException(roomId, reservation.getDate());
        }
        reservation.setRoom(room);

        return reservationRepo.save(reservation);
    }

    private boolean isInConfict(ReservationEntity reservation, Long roomId) {
        List<ReservationEntity> existingReservations = reservationRepo.findAllByRoomIdAndDate(roomId, reservation.getDate());
        for (ReservationEntity existingReservation : existingReservations) {
            if ((reservation.getStartTime() < existingReservation.getEndTime() && reservation.getStartTime() >= existingReservation.getStartTime())
                    || (reservation.getEndTime() > existingReservation.getStartTime() && reservation.getEndTime() <= existingReservation.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        reservationRepo.delete(id);
    }

}
