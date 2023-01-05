package com.sk.testTask.Repository;

import com.sk.testTask.Entity.JPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAEntityRepository extends JpaRepository<JPAEntity,Long> {
}
