package com.sk.testTask.Service;

import com.sk.testTask.Entity.JPAEntity;
import com.sk.testTask.MappingObjects.Adder;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Repository.JPAEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.util.NoSuchElementException;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class EntityServiceImplTest {

    @Mock
    private JPAEntityRepository repository;
    @InjectMocks
    private EntityServiceImpl service;

    private JPAEntity entity;
    private Adder adder,failingAdder;
    @BeforeEach
    void setUp() {
        entity=new JPAEntity(1,new Counter(5));
        adder=new Adder(1,5);
        failingAdder=new Adder(0,5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void successfulUpdate() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Assertions.assertEquals(service.update(adder.getId(),adder.getAdd()).getCurrent(),10);
    }

    @Test
    void failUpdate(){
        Mockito.when(repository.findById(0L)).thenThrow(new NoSuchElementException());
        Assertions.assertThrows(DataAccessException.class,()->{
            service.update(failingAdder.getId(), failingAdder.getAdd());
        });
    }
}