package com.example.demo.dao;

import com.example.demo.models.Person;
import com.example.demo.models.Stars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Math.abs;

@Repository("person")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "SELECT * from people ORDER BY birth LIMIT 20";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            BigDecimal birth = resultSet.getBigDecimal("birth");
            String name = resultSet.getString("name");
            return new Person(name, id, birth);
        }));
    }

    @Override
    public String insertPerson(Person person) throws SQLException {
        final String query = "INSERT INTO people (id, name, birth) VALUES (?, ?, ?)";
        Integer newPersonId = abs(new Random().nextInt());
        jdbcTemplate.update(query, new Object[]{newPersonId, person.getName(), person.getBirth()}, new int[]{Types.INTEGER, Types.VARCHAR, Types.BIGINT});
        return String.format("Person successfully created with id %s", newPersonId);
    }

    @Override
    public void deletePerson(Integer id) {
        final String sql = "DELETE FROM people WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Stars> selectPersonById(Integer id) {
        final String query = "SELECT * FROM stars WHERE person_id = ?";
        List<Stars> stars = jdbcTemplate.query(query, new Object[]{id}, (resultSet, i) -> {
            System.out.print(resultSet);
            int personId = resultSet.getInt("person_id");
            int movieId = resultSet.getInt("movie_id");
            return new Stars(personId, movieId);
        });
        if(stars.size() != 0) {
            return Optional.ofNullable(stars.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Person>> selectPersonByName(String name) {
        final String query = "SELECT * FROM people WHERE lower(name) LIKE ?";
        String searchText = "%" + name.toLowerCase();
        List<Person> persons = jdbcTemplate.query(query, new Object[]{searchText}, (resultSet, i) -> {
            Integer personId = resultSet.getInt("id");
            BigDecimal birth = resultSet.getBigDecimal("birth");
            String personName = resultSet.getString("name");
            return new Person(personName, personId, birth);
        });
        return Optional.of(persons);
    }

    @Override
    public void updatePerson(Integer id, Person person) {
        final String updateQuery = "UPDATE people SET name = ?, birth = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, person.getName(), person.getBirth(), id);
    }
}
