package com.example.demo.jacoco.person;

import com.example.demo.jacoco.exception.FunctionalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PersonDTO> getPersons() {
        return personService.getPersons();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PersonDTO getPerson(@PathVariable long id) throws FunctionalException {

        return personService.getPerson(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addPerson(@Valid @RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updatePerson(
            @Valid @RequestBody PersonDTO personDTO,
            @PathVariable long id) throws FunctionalException {

        personService.updatePerson(personDTO, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) throws FunctionalException {

        personService.delete(id);
    }
}
