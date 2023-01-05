package com.sk.testTask.Service;

import com.sk.testTask.Entity.JPAEntity;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Repository.JPAEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
class EntityServiceImplTest {

    @MockBean
    private JPAEntityRepository repository;

    private EntityService service;
    @BeforeEach
    void setUp() {
        JPAEntity entity=new JPAEntity(1,new Counter(5));
        Adder  
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.findById(1L)).thenThrow(new NoSuchElementException());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void successfulUpdate() {
        Assertions.assertEquals(service);
    }
}