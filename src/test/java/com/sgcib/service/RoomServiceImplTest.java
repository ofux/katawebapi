package com.sgcib.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.sgcib.model.room.RoomEntity;
import com.sgcib.model.room.RoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class RoomServiceImplTest {

    @Inject
    private RoomRepository repo;

    @Inject
    private RoomService service;

    @Test
    public void testGetAll() throws Exception {
        // Arrange
        insertRoom("room0");
        insertRoom("room1");

        // Act
        Page<RoomEntity> result = service.getAll(null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        // Arrange

        // Act
        Page<RoomEntity> result = service.getAll(null);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(0);
    }

    @Test
    public void testGetOne() throws Exception {
        // Arrange
        RoomEntity room = insertRoom("room0");
        insertRoom("room1");

        // Act
        RoomEntity result = service.getOne(room.getId());

        // Assert
        assertThat(result.getId()).isEqualTo(room.getId());
    }

    @Test
    public void testGetOneNotFound() throws Exception {
        // Arrange
        insertRoom("room0");
        insertRoom("room1");

        // Act
        RoomEntity result = service.getOne(-50L);

        // Assert
        assertThat(result).isNull();
    }

    private RoomEntity insertRoom(String name) {
        RoomEntity room = new RoomEntity();
        room.setName(name);
        return repo.save(room);
    }
}
