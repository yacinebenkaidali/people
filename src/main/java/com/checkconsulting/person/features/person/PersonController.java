package com.checkconsulting.person.features.person;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonResponse> getAllPersons(@RequestParam("lastName") Optional<String> lastName,
            @RequestParam("firstName") Optional<String> firstName, @RequestParam("minAge") Optional<Integer> minAge,
            @RequestParam("maxAge") Optional<Integer> maxAge) {
        return this.personService.getAllPersons(lastName, firstName, minAge, maxAge);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable String id) {
        this.personService.deletePersonById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable String id,
            @Valid @RequestBody PersonUpdateDto personUpdateDto) {
        return this.personService.updatePerson(id, personUpdateDto);
    }

    @PostMapping()
    public Person createPerson(@Valid @RequestBody PersonDto person) {
        var p = new Person();
        p.setAge(person.getAge());
        p.setEmail(person.getEmail());
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        return this.personService.createPerson(p);
    }

}
