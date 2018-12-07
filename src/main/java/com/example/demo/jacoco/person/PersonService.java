package com.example.demo.jacoco.person;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    public List<PersonDTO> getPersons() {

        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(personMapper::personToPersonDTO)
                .collect(Collectors.toList());

    }

}
