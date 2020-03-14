package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOfficerDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    public List<Officer> findAll() {
        return jdbcTemplate.query("select * from officers",
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        );
    }
}
