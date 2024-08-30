package ru.luckyhunterzzz.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.luckyhunterzzz.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.luckyhunterzzz.springcourse.constants.Constants.*;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() throws SQLException {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query(SELECT_ALL_WHERE_ID,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query(SELECT_ALL_WHERE_EMAIL,
                        new BeanPropertyRowMapper<>(Person.class),
                        email)
                .stream()
                .findFirst();
    }

    public void save(Person person) {
        jdbcTemplate.update(INSERT_INTO_PERSONS,
                person.getName(),
                person.getAge(),
                person.getEmail(),
                person.getAddress()
        );
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(
                UPDATE,
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                updatedPerson.getAddress(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE, id);
    }
}
