package com.example.demo.api;

import com.example.demo.models.Person;
import com.example.demo.models.Stars;
import com.example.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/people")
@RestController
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @PostMapping
    public String addPerson(@Valid @NonNull @RequestBody Person person) throws SQLException {
        return personService.insertPerson(person);
    }

    @GetMapping(path = "{id}")
    public Optional<Stars> getPersonById(@PathVariable("id") Integer id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/search")
    public List<Person> getPersonByName(@RequestParam("name") String name) {
        return personService.getPersonByName(name).orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") Integer id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }
}
