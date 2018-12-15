package com.example.demo.jacoco.person;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonResourceAssembler implements ResourceAssembler<PersonDTO, Resource<PersonDTO>> {

    @Override
    public Resource<PersonDTO> toResource(PersonDTO personDTO) {
        return new Resource<>(personDTO,
                linkTo(methodOn(PersonResource.class).getPerson(personDTO.getId())).withSelfRel(),
                linkTo(methodOn(PersonResource.class).getPersons()).withRel("persons"));
    }
}
