package com.checkconsulting.person.features.person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getAllPersons(Optional<String> lastName, Optional<String> firstName,
            Optional<Integer> minAge,
            Optional<Integer> maxAge) {

        var people = this.personRepository
                .findAll(PersonSpecification.multiCriteriaSearch(lastName, firstName, minAge, maxAge));
        List<PersonResponse> peopleRes = people.stream().map(p -> PersonResponse.builder()
                .age(p.getAge())
                .email(p.getEmail())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .nbBoughtProducts(p.products.size())
                .products(p.products.stream().map(prod -> prod.getName()).collect(Collectors.joining(" || ")))
                .build()).collect(Collectors.toList());
        return peopleRes;
    }

    public Optional<Person> getPersonById(String email) {
        return this.personRepository.findById(email);
    }

    public void deletePersonById(String email) {
        this.personRepository.deleteById(email);
    }

    public Person createPerson(Person person) {
        return this.personRepository.save(person);
    }

    public ResponseEntity<Person> updatePerson(String email, PersonUpdateDto newPerson) {
        var p = this.personRepository.findById(email);
        if (p.isPresent()) {
            var person = p.get();
            if (newPerson.getFirstName() != null) {
                person.setFirstName(newPerson.getFirstName());
            }
            if (newPerson.getLastName() != null) {
                person.setLastName(newPerson.getLastName());
            }
            if (newPerson.getAge() != 0) {
                person.setAge(newPerson.getAge());
            }
            return new ResponseEntity<>(this.personRepository.save(person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
