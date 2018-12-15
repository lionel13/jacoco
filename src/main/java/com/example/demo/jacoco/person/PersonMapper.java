package com.example.demo.jacoco.person;

import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonDTO personToPersonDTO(Person person);

    Person personDTOToPerson(PersonDTO personDTO);

}
