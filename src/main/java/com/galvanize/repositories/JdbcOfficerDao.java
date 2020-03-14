package com.galvanize.repositories;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOfficerDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertOfficer;
    @Autowired
    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    public Long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    public boolean exists(long id) {
        boolean found = false;
        try{
            found = jdbcTemplate.queryForObject("select 1 from officers where id = ?", boolean.class, id);
        } catch(EmptyResultDataAccessException e) {}
        return found;
    }

    public List<Officer> findAll() {
        return jdbcTemplate.query("select * from officers",
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        );
    }

    public Optional<Officer>  findById(long id) {
        if (!exists(id)) return Optional.empty();
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from officers where id = ?",
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name")),id)
        );
    }

    public Officer save(Officer officer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("officer_rank", officer.getRank());
        parameters.put("first_name", officer.getFirstName());
        parameters.put("last_name", officer.getLastName());

        long newId = insertOfficer.executeAndReturnKey(parameters).longValue();
        officer.setId(newId);

        return officer;
    }
}
