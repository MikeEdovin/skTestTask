package com.sk.testTask.Service;

import com.sk.testTask.Entity.JPAEntity;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Repository.JPAEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService{
    @Autowired
    JPAEntityRepository repository;
    @Override
    public Counter update(long id, long add) throws DataAccessException, NoSuchElementException{
        JPAEntity entity=repository.findById(id).get();
        Counter counter=entity.getCounter();
        long current=counter.getCurrent()+add;
        counter.setCurrent(current);
        entity.setCounter(counter);
        return repository.save(entity).getCounter();

    }
}
