package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @Test
    void findAllOfficers() {
        List<Officer> officers = jpaOfficerDao.findAll();
        assertFalse(officers.isEmpty());
    }

    

}