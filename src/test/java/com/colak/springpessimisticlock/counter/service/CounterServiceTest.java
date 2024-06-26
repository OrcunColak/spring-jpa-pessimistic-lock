package com.colak.springpessimisticlock.counter.service;

import com.colak.springpessimisticlock.counter.jpa.Counter;
import com.colak.springpessimisticlock.counter.repository.CounterRepository;
import com.colak.springpessimisticlock.counter.service.CounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This test will re-create the DB
@SpringBootTest
class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @Autowired
    private CounterRepository counterRepository;

    @Test
    void testIncrementCounter() {
        int incrementedCounter = counterService.incrementCounter(1);
        Counter counter = counterRepository.findById(1).orElseThrow();
        assertEquals(counter.getCount(), incrementedCounter);

        // Here, Counter = 1, Version = 1
        // Now Counter = 1, Version = 2
        Counter newCounter = counterService.findByIdForWriteForceVersionIncrement(1);
        assertEquals(incrementedCounter + 1, newCounter.getVersion());

    }
}
