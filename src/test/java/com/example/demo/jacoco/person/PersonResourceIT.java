package com.example.demo.jacoco.person;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.jacoco.JacocoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JacocoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonResourceIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    @Sql({
            "/sql/drop_person.sql",
            "/sql/create_person.sql",
            "/sql/populate_person.sql"
    })
    public void gerPersons() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List<PersonDTO>> response = restTemplate.exchange(
                createURLWithPort("/api/person"),
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<PersonDTO>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<PersonDTO> body = response.getBody();

        assertEquals(2, body.size());
        PersonDTO personDTO1 = body.get(0);
        assertEquals("Audibert", personDTO1.getNom());
        assertEquals("Lionel", personDTO1.getPrenom());
        assertEquals("MONSIEUR", personDTO1.getCivilite());
        assertEquals("l.audibert@free.fr", personDTO1.getMail());
        assertEquals(31L, personDTO1.getAge().longValue());
        PersonDTO personDTO2 = body.get(1);
        assertEquals("Doe", personDTO2.getNom());
        assertEquals("Jane", personDTO2.getPrenom());
        assertEquals("MADAME", personDTO2.getCivilite());
        assertEquals("j.doe@zaza.fr", personDTO2.getMail());
        assertEquals(55L, personDTO2.getAge().longValue());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}