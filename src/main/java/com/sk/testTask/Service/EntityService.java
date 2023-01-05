package com.sk.testTask.Service;

import com.sk.testTask.MappingObjects.Counter;

public interface EntityService {
    Counter update(long id, long add);
}
