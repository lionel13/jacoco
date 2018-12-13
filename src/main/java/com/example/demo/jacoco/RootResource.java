package com.example.demo.jacoco;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jacoco.person.PersonResource;

@RestController
@RequestMapping("/")
public class RootResource {

    @GetMapping
    ResponseEntity<ResourceSupport> root() {

        ResourceSupport resourceSupport = new ResourceSupport();

        resourceSupport.add(linkTo(methodOn(RootResource.class).root()).withSelfRel());
        resourceSupport.add(linkTo(methodOn(PersonResource.class).getPersons()).withRel("persons"));

        return ResponseEntity.ok(resourceSupport);
    }
}
