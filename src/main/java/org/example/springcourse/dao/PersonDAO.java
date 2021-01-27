package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY name ASC",
                new BeanPropertyRowMapper<>(Person.class));

    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                            new Object[]{id},
                            new BeanPropertyRowMapper<>(Person.class))
                            .stream()
                            .findAny().orElse(null);
    }

    public void save(Person p) {
        jdbcTemplate.update("INSERT INTO person (" +
                        "name, age, email) VALUES(?, ?, ?)",
                            p.getName(),
                            p.getAge(),
                            p.getEmail());
    }

    public void update(Person p) {
        jdbcTemplate.update("update person set " +
                        "name = ?, age = ?, email = ? where id = ?",
                            p.getName(),
                            p.getAge(),
                            p.getEmail(),
                            p.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

}
