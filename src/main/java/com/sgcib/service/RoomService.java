package com.sgcib.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sgcib.model.room.RoomEntity;

public interface RoomService {

    Page<RoomEntity> getAll(Pageable pageable);

    RoomEntity getOne(Long id);
}
