package com.colak.springtutorial.counter.repository;

import com.colak.springtutorial.counter.jpa.Counter;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Counter c where c.id = :id")
    Optional<Counter> lockByIdForRead(Integer id);

    // See https://medium.com/indonesian-developer/understanding-query-hints-for-better-performance-fbaa5364df8f
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000"), // Lock timeout in milliseconds
            @QueryHint(name = "jakarta.persistence.query.timeout", value = "5000") // Query timeout in milliseconds
    })
    @Query("select c from Counter c where c.id = :id ")
    Optional<Counter> lockByIdForWrite(Integer id);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("select c from Counter c where c.id = :id")
    Optional<Counter> lockByIdForWriteForceVersionIncrement(Integer id);
}
