package com.sgcib.model.reservation;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<ReservationEntity, Long> {

    Page<ReservationEntity> findAllByRoomIdAndDate(Long roomId, Date date, Pageable pageable);

    Page<ReservationEntity> findAllByRoomId(Long roomId, Pageable pageable);

    Page<ReservationEntity> findAllByDate(Date date, Pageable pageable);
}
