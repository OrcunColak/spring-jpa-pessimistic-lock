package com.colak.springpessimisticlock.counter.repository;

import com.colak.springpessimisticlock.counter.jpa.Counter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Counter c where c.id = :id")
    Optional<Counter> lockByIdForRead(Integer id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Counter c where c.id = :id ")
    Optional<Counter> lockByIdForWrite(Integer id);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("select c from Counter c where c.id = :id")
    Optional<Counter> lockByIdForWriteForceVersionIncrement(Integer id);
}
