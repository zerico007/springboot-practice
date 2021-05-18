package com.example.demo.services;
import com.example.demo.dao.PersonDao;
import com.example.demo.models.Person;
import com.example.demo.models.Stars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPeople() {
        return personDao.getAllPeople();
    }

    public String insertPerson(Person person) throws SQLException {
        return personDao.insertPerson(person);
    }

    public Optional<Stars> getPersonById(Integer id){
        return personDao.selectPersonById(id);
    }

    public Optional<List<Person>> getPersonByName(String name) {
        return personDao.selectPersonByName(name);
    }

    public void deletePerson(Integer id) {
        personDao.deletePerson(id);
    }

    public void updatePerson(Integer id, Person person) {
        personDao.updatePerson(id, person);
    }

}
