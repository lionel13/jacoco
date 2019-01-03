package com.example.demo.jacoco.person;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    List<PersonDTO> getPersons() {

        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(personMapper::personToPersonDTO)
                .collect(Collectors.toList());

    }

    public void addPerson(PersonDTO personDTO) {
        personRepository.save(personMapper.personDTOToPerson(personDTO));
    }

    public PersonDTO getPerson(long personId) throws PersonNotFoundException {

        return personRepository.findById(personId).map(
                personMapper::personToPersonDTO)
                .orElseThrow(() -> new PersonNotFoundException(personId));

    }

    public void updatePerson(PersonDTO personDTO, long personId) throws PersonNotFoundException {

        personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        Person personToUpdate = personMapper.personDTOToPerson(personDTO);
        personToUpdate.setId(personId);
        personRepository.save(personToUpdate);

    }

    public void delete(long personId) throws PersonNotFoundException {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(personId));

        personRepository.delete(person);
    }

}
