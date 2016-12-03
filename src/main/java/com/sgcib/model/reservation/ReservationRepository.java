package com.sgcib.model.reservation;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationRepository extends PagingAndSortingRepository<ReservationEntity, Long> {

    Page<ReservationEntity> findAllByRoomIdAndDate(Long roomId, Date date, Pageable pageable);

    List<ReservationEntity> findAllByRoomIdAndDate(Long roomId, Date date);

    Page<ReservationEntity> findAllByRoomId(Long roomId, Pageable pageable);

    Page<ReservationEntity> findAllByDate(Date date, Pageable pageable);
}
