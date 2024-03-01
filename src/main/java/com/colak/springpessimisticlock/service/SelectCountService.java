package com.colak.springpessimisticlock.service;

import com.colak.springpessimisticlock.repository.SelectCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SelectCountService {

    private final SelectCountRepository repository;

    @Transactional
    public long exclusiveCountByOwnerId(int id) {
        return repository.exclusiveCountByOwnerId(id);
    }

    @Transactional
    public long exclusiveCountByOwnerIdNative(int id) {
        return repository.exclusiveCountByOwnerIdNative(id);
    }
}
