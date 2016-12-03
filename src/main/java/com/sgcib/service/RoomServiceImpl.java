package com.sgcib.service;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomRepository repo;

    @Override
    public Page<RoomEntity> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public RoomEntity getOne(Long id) {
        return repo.findOne(id);
    }

}
