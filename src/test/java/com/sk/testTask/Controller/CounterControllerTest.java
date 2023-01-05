package com.sk.testTask.Controller;

import com.sk.testTask.MappingObjects.Adder;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Service.EntityServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CounterController.class)

class CounterControllerTest {

    @InjectMocks
    private EntityServiceImpl service;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void update() throws Exception {
        Adder adder=new Adder(1,5);
        Counter counter=new Counter(5);
        Mockito.when(service.update(adder.getId(), adder.getAdd())).thenReturn(counter);

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content())
                .andExpect(status().isCreated());
    }
}