package com.example.demo.jacoco.person;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> getPersons() {

        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(personMapper::personToPersonDTO)
                .collect(Collectors.toList());

    }

}
