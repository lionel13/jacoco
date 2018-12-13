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

    List<PersonDTO> getPersons() {

        return personRepository.findAll().stream()
                .map(personMapper::personToPersonDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO getPerson(long personId) {

        return personRepository.findById(personId).map(
                personMapper::personToPersonDTO)
                .orElseThrow(() -> new PersonNotFoundException(personId));

    }

    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personRepository.saveAndFlush(personMapper.personDTOToPerson(personDTO));

        return personMapper.personToPersonDTO(person);

    }

    public PersonDTO updatePerson(PersonDTO personDTO, long personId) {

        return personRepository.findById(personId)
                .map(person -> {
                    Person personToUpdate = personMapper.personDTOToPerson(personDTO);
                    return personMapper.personToPersonDTO(personRepository.save(personToUpdate));
                })
                .orElseThrow(() -> new PersonNotFoundException(personId));

    }

    public void delete(long personId) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        personRepository.delete(person);
    }

}
