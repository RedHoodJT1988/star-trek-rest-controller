package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {
    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    @Transactional
    void count(){
        Long count = jdbcOfficerDao.count();
        assertEquals(5L, count);
    }

    @Test
    @Transactional
    void findAllOfficers() {
        List<Officer> officers = jdbcOfficerDao.findAll();
        assertFalse(officers.isEmpty());
    }

    @Test
    @Transactional
    void existsById() {
        boolean exists = jdbcOfficerDao.exists(2L);
        assertTrue(exists);
    }

    @Test
    @Transactional
    void findOfficerById() {
        Optional<Officer> officer = jdbcOfficerDao.findById(3L);
        assertTrue(officer.isPresent());
        assertNotNull(officer.get().getId());
    }

    @Test
    @Transactional
    void createNewOfficer() {
        Officer officer = new Officer(Rank.ADMIRAL, "Al", "Simmons");
        officer = jdbcOfficerDao.save(officer);
        assertNotNull(officer);
        assertNotNull(officer.getId());
    }

    @Test
    @Transactional
    void deleteOfficer() {
        Officer officer = new Officer(Rank.ADMIRAL, "Al", "Simmons");
        officer = jdbcOfficerDao.save(officer);
        long id = officer.getId();
        assertNotNull(id);

        jdbcOfficerDao.delete(id);
        assertFalse(jdbcOfficerDao.exists(id));
    }
}