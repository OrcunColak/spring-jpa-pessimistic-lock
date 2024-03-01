package com.colak.springpessimisticlock.service;

import com.colak.springpessimisticlock.jpa.Counter;
import com.colak.springpessimisticlock.repository.CounterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CounterService {

    private final CounterRepository counterRepository;

    @Transactional
    public Counter findByIdForRead(int id) {
        return counterRepository.lockByIdForRead(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
    }

    @Transactional
    public Counter findByIdForWriteForceVersionIncrement(int id) {
        // select id, counter_value, version from counter c where c.id=? for no key update
        // update counter set version=? where id=? and version=?
        return counterRepository.lockByIdForWriteForceVersionIncrement(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));
    }

    @Transactional
    public int incrementCounter(int id) {
        // select id, counter_value, version from counter c where c.id=? for no key update
        // update counter set counter_value=?, version=? where id=? and version=?
        Counter counter = counterRepository.lockByIdForWrite(id)
                .orElseThrow(() -> new EntityNotFoundException(getNotFoundMessage(id)));

        int result = counter.getCount() + 1;
        counter.setCount(result);
        counterRepository.save(counter);
        return result;
    }

    private String getNotFoundMessage(int id) {
        return "Counter " + id + " not found";
    }
}
