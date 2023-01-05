package com.sk.testTask.Service;

import com.sk.testTask.Entity.JPAEntity;
import com.sk.testTask.MappingObjects.Counter;
import com.sk.testTask.Repository.JPAEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.LockModeType;
import java.util.NoSuchElementException;

@Service
public class EntityServiceImpl implements EntityService{
    @Autowired
    JPAEntityRepository repository;
    @Override
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Transactional
    public Counter update(long id, long add) throws DataAccessException, NoSuchElementException{
        JPAEntity entity=repository.findById(id).get();
        Counter counter=entity.getCounter();
        long current=counter.getCurrent()+add;
        counter.setCurrent(current);
        entity.setCounter(counter);
        return repository.save(entity).getCounter();

    }
}
