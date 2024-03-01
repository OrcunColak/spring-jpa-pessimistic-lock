package com.colak.springpessimisticlock.repository;

import com.colak.springpessimisticlock.jpa.Counter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

    @Query("select c from Counter c where c.id = :id")
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Counter> lockByIdForRead(Integer id);

    @Query("select c from Counter c where c.id = :id ")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Counter> lockByIdForWrite(Integer id);

    @Query("select c from Counter c where c.id = :id")
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    Optional<Counter> lockByIdForWriteForceVersionIncrement(Integer id);
}
