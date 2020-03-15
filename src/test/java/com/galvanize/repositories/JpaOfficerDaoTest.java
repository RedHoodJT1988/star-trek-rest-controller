package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaOfficerDaoTest {
    @Autowired
    JpaOfficerDao jpaOfficerDao;

    @Test
    @Transactional
    void count() {
        Long count = jpaOfficerDao.count();
        assertEquals(5L, count);
    }
    @Test
    @Transactional
    void findAllOfficers() {
        List<Officer> officers = jpaOfficerDao.findAll();
        assertFalse(officers.isEmpty());
    }

    @Test
    @Transactional
    void existsById() {
        boolean exists = jpaOfficerDao.existsById(2L);
        assertTrue(exists);
    }

    @Test
    @Transactional
    void findOfficerById() {
        Optional<Officer> officer = jpaOfficerDao.findById(3L);
        assertTrue(officer.isPresent());
        assertNotNull(officer.get().getId());
    }

    @Test
    @Transactional
    void createNewOfficer() {
        Officer officer = new Officer(Rank.CAPTAIN, "Al", "Simmons");
        jpaOfficerDao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    @Transactional
    void deleteOfficer() {
        Officer officer = new Officer(Rank.ADMIRAL, "Al", "Simmons");
        officer = jpaOfficerDao.save(officer);
        long id = officer.getId();
        assertNotNull(id);
    }

    @Test
    @Transactional
    void findByRank() {
        List<Officer>captains = jpaOfficerDao.findAllByRank(Rank.CAPTAIN);
        assertFalse(captains.isEmpty());
    }


}