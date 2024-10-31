package com.colak.springtutorial.counter.repository;

import com.colak.springtutorial.counter.jpa.Counter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * See <a href="https://medium.com/@hweemyoung/aggregate-function-and-lock-in-hibernate-11efb935b4eb">...</a>
 */
@Repository
public interface SelectCountRepository extends JpaRepository<Counter, Integer> {

    // This is incorrect because it generates
    // select  count(c.id) from counter c where c.id=?
    // So when there is an aggregate function @Lock does not work
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select count(c) from Counter c where c.id = :id")
    long exclusiveCountByOwnerId(Integer id);


    // Use native query instead
    // This does not work
    // select count(*) from Counter as c where c.id = :id for update
    // ERROR: FOR UPDATE is not allowed with aggregate functions

    // But this works
    // select count(0) from (select 0 from Counter as c where c.id = :id for update)
    @Query(value = "select count(0) from (select 0 from Counter as c  where c.id = :id for update)", nativeQuery = true)
    long exclusiveCountByOwnerIdNative(Integer id);


}
