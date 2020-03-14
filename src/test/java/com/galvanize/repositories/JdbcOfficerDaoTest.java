package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {
    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    void count(){
        Long count = jdbcOfficerDao.count();
        assertEquals(5L, count);
    }

    @Test
    void findAllOfficers() {
        List<Officer> officers = jdbcOfficerDao.findAll();
        assertFalse(officers.isEmpty());
    }

    @Test
    void existsById() {
        boolean exists = jdbcOfficerDao.exists(2L);
        assertTrue(exists);
    }

    @Test
    void findOfficerById() {
        Optional<Officer> officer = jdbcOfficerDao.findById(3L);
        assertTrue(officer.isPresent());
        assertNotNull(officer.get().getId());
    }

    @Test
    void createNewOfficer() {
        Officer officer = new Officer(Rank.ADMIRAL, "Al", "Simmons");
        officer = jdbcOfficerDao.save(officer);
        assertNotNull(officer);
        assertNotNull(officer.getId());
    }
}