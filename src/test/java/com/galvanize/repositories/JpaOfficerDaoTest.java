package com.galvanize.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaOfficerDaoTest {
    @Autowired
    JpaOfficerDao jpaOfficerDao;

    @Test
    void count() {
        Long count = jpaOfficerDao.count();
        assertEquals(5L, count);
    }

}