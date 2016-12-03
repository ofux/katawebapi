package com.sgcib.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sgcib.model.reservation.ReservationEntity;

public interface ReservationService {

    Page<ReservationEntity> getAll(Long roomId, Date date, Pageable pageable);

    ReservationEntity getOne(Long id);

    ReservationEntity create(ReservationEntity reservation, Long roomId) throws ReservationConfictException;

    void delete(Long id);
}
