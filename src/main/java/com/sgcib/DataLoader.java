package com.sgcib;

import javax.inject.Inject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Inject
    private RoomRepository roomRepository;

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 10; i++) {
            RoomEntity room = new RoomEntity();
            room.setName("room" + i);
            roomRepository.save(room);
        }
    }
}
