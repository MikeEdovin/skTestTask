package com.sk.testTask.Repository;

import com.sk.testTask.Entity.JPAEntity;
import com.sk.testTask.MappingObjects.Counter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JPAEntityRepositoryTest {

    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:14.5")
            .withDatabaseName("sk_example_db")
            .withPassword("12345")
            .withUsername("sk_example_user");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
    }

    @Autowired
    JPAEntityRepository repository;

    private JPAEntity entity;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        Assertions.assertEquals(repository.findById(1L).get().getCounter().getCurrent(), 0);
    }

    @Test
    void failFindById() {
        Assertions.assertEquals(repository.findById(0L), Optional.empty());
        Assertions.assertThrows(DataAccessException.class, () -> {
            repository.findById(null);
        });

    }

    @Test
    void save() {
        JPAEntity entity = new JPAEntity();
        entity.setCounter(new Counter(10));
        Assertions.assertEquals(repository.save(entity), entity);
    }

    @Test
    void failSave() {
        Assertions.assertThrows(DataAccessException.class, () -> {
            repository.save(null);
        });
    }
}