package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
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

    @Test
    void createNewOfficer() {
        Officer officer = new Officer(Rank.CAPTAIN, "Bruce", "Wayne");
        jpaOfficerDao.save(officer);
        assertNotNull(officer.getId());
        jpaOfficerDao.delete(officer);
    }


}