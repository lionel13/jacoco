package com.example.demo.jacoco.person;

import com.example.demo.jacoco.util.RandomObjectFiller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonResource.class)
public class PersonResourceUT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @Test
    public void test() throws Exception {
        RandomObjectFiller randomObjectFiller = new RandomObjectFiller();

        Person andFill = randomObjectFiller.createAndFill(Person.class);

        System.out.println(andFill);
    }


    @Test
    public void getPersonsEmpty() throws Exception {

        given(personService.getPersons()).willReturn(new ArrayList<>());

        this.mvc.perform(get("/api/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        verify(personService, times(1)).getPersons();
        verifyNoMoreInteractions(personService);

    }

    @Test
    public void getPersons() throws Exception {
        PersonDTO personDTO1 = new PersonDTO();
        personDTO1.setNom("Audibert");
        personDTO1.setPrenom("Lionel");
        personDTO1.setCivilite("MONSIEUR");
        personDTO1.setMail("l.audibert@free.fr");
        personDTO1.setAge("31");
        PersonDTO personDTO2 = new PersonDTO();
        personDTO2.setNom("Jane");
        personDTO2.setPrenom("Doe");
        personDTO2.setCivilite("MADAME");
        personDTO2.setMail("j.doe@zaza.fr");
        personDTO2.setAge("55");

        List<PersonDTO> personDTOs = Collections.unmodifiableList(Arrays.asList(personDTO1, personDTO2));

        given(personService.getPersons()).willReturn(personDTOs);

        this.mvc.perform(get("/api/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nom", is(personDTO1.getNom())))
                .andExpect(jsonPath("$[0].prenom", is(personDTO1.getPrenom())))
                .andExpect(jsonPath("$[0].civilite", is(personDTO1.getCivilite())))
                .andExpect(jsonPath("$[0].mail", is(personDTO1.getMail())))
                .andExpect(jsonPath("$[0].age", is(personDTO1.getAge())))
                .andExpect(jsonPath("$[1].nom", is(personDTO2.getNom())))
                .andExpect(jsonPath("$[1].prenom", is(personDTO2.getPrenom())))
                .andExpect(jsonPath("$[1].civilite", is(personDTO2.getCivilite())))
                .andExpect(jsonPath("$[1].mail", is(personDTO2.getMail())))
                .andExpect(jsonPath("$[1].age", is(personDTO2.getAge())))
        ;

        verify(personService, times(1)).getPersons();
        verifyNoMoreInteractions(personService);

    }

}