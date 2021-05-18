package com.example.demo.dao;

import com.example.demo.models.Person;
import com.example.demo.models.Stars;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonDao {

    List<Person> getAllPeople();

    String insertPerson(Person person) throws SQLException;

    void deletePerson(Integer id);

    Optional<Stars> selectPersonById(Integer id);

    Optional<List<Person>> selectPersonByName(String name);

    void updatePerson(Integer id, Person person);
}
