package com.colak.springpessimisticlock.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This test will re-create the DB
@SpringBootTest
class SelectCountServiceTest {

    @Autowired
    private SelectCountService selectCountService;

    @Test
    void exclusiveCountByOwnerId() {
        long incrementedCounter = selectCountService.exclusiveCountByOwnerId(1);
        assertEquals(1, incrementedCounter);
    }

    @Test
    void exclusiveCountByOwnerIdNative() {
        long incrementedCounter = selectCountService.exclusiveCountByOwnerIdNative(1);
        assertEquals(1, incrementedCounter);
    }
}
