package com.example.demo.jacoco.person;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jacoco.utils.ControlResourceUtil;

@RestController
@RequestMapping("/api/person")
public class PersonResource {

    private PersonService personService;

    private final PersonResourceAssembler personResourceAssembler;

    public PersonResource(PersonService personService, PersonResourceAssembler personResourceAssembler) {
        this.personService = personService;
        this.personResourceAssembler = personResourceAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resources<Resource<PersonDTO>> getPersons() {

        List<PersonDTO> persons = personService.getPersons();

        List<Resource<PersonDTO>> persons1 = persons.stream()
                .map(personResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(persons1,
                linkTo(methodOn(PersonResource.class).getPersons()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resource<PersonDTO> getPerson(@PathVariable String id) {

        int personId = ControlResourceUtil.controlId(id);
        PersonDTO person = personService.getPerson(personId);

        return personResourceAssembler.toResource(person);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resource<PersonDTO>> addPerson(
            @RequestBody PersonDTO personDTO) throws URISyntaxException {
        PersonDTO addedPerson = personService.addPerson(personDTO);

        Resource<PersonDTO> resource = personResourceAssembler.toResource(addedPerson);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resource<PersonDTO> updatePerson(
            @RequestBody PersonDTO personDTO,
            @PathVariable long id) {

        PersonDTO updatedPerson = personService.updatePerson(personDTO, id);
        return personResourceAssembler.toResource(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
