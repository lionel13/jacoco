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
                .orElseThrow(() -> new PersonNotFoundException("Personne non trouvée pour l'id :: " + personId));

    }

    public void updatePerson(PersonDTO personDTO, long personId) throws PersonNotFoundException {

        personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Personne non trouvée pour l'id :: " + personId));

        Person personToUpdate = personMapper.personDTOToPerson(personDTO);
        personToUpdate.setId(personId);
        personRepository.save(personToUpdate);

    }

    public void delete(long personId) throws PersonNotFoundException {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Personne non trouvée pour l'id :: " + personId));

        personRepository.delete(person);
    }

}
