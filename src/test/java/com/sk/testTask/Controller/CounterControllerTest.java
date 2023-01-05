package com.sk.testTask.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.testTask.MappingObjects.Adder;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Service.EntityService;
import com.sk.testTask.Service.EntityServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CounterController.class)

class CounterControllerTest {

    @MockBean
    private EntityService service;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

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
                        .content(mapper.writeValueAsString(adder)))
                        .andExpect(status().isOk())
                        .andDo(print()).andReturn();
    }
}