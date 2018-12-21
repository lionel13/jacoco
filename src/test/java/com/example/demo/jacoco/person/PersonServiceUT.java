package com.example.demo.jacoco.person;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersonService.class, PersonMapperImpl.class })
public class PersonServiceUT {

    private PersonService personService;

    public PersonServiceUT(PersonService personService) {
        this.personService = personService;
    }

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void getPersons() {

        Person person1 = new Person();
        person1.setNom("Audibert");
        person1.setPrenom("Lionel");
        person1.setCivilite("MONSIEUR");
        person1.setMail("l.audibert@free.fr");
        person1.setAge(31);
        Person person2 = new Person();
        person2.setNom("Jane");
        person2.setPrenom("Doe");
        person2.setCivilite("MADAME");
        person2.setMail("j.doe@zaza.fr");
        person2.setAge(55);

        List<Person> persons = Collections.unmodifiableList(Arrays.asList(person1, person2));

        given(personRepository.findAll()).willReturn(persons);

        List<PersonDTO> persons1 = personService.getPersons();
        Assert.assertEquals(2, persons1.size());
    }
}