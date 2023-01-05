package com.sk.testTask.Repository;

import com.sk.testTask.Entity.JPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface JPAEntityRepository extends JpaRepository<JPAEntity,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    Optional<JPAEntity> findById(Long id);
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    JPAEntity save(JPAEntity entity);
}
