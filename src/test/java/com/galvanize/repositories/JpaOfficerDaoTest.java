package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;
import java.util.Optional;

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
    void existsById() {
        boolean exists = jpaOfficerDao.existsById(2L);
        assertTrue(exists);
    }

    @Test
    void findOfficerById() {
        Optional<Officer> officer = jpaOfficerDao.findById(3L);
        assertTrue(officer.isPresent());
        assertNotNull(officer.get().getId());
    }

    @Test
    void createNewOfficer() {
        Officer officer = new Officer(Rank.CAPTAIN, "Al", "Simmons");
        jpaOfficerDao.save(officer);
        assertNotNull(officer.getId());
        jpaOfficerDao.delete(officer);
    }

    @Test
    void deleteOfficer() {
        Officer officer = new Officer(Rank.ADMIRAL, "Al", "Simmons");
        officer = jpaOfficerDao.save(officer);
        long id = officer.getId();
        assertNotNull(id);

        jpaOfficerDao.delete(officer);
        assertEquals(false, jpaOfficerDao.existsById(id));
    }

    @Test
    void findByRank() {
        List<Officer>captains = jpaOfficerDao.findAllByRank(Rank.CAPTAIN);
        assertFalse(captains.isEmpty());
    }


}