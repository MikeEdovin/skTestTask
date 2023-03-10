package com.sk.testTask.Controller;

import com.sk.testTask.MappingObjects.Adder;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.NoSuchElementException;

@RestController
public class CounterController {
    @Autowired
    EntityService service;
    @PostMapping("/add")
    public ResponseEntity<Counter> update(@RequestBody Adder adder){
        try{
            Counter counter=service.update(adder.getId(), adder.getAdd());
            return ResponseEntity.ok().body(counter);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(418).build();
        }
    }
}
