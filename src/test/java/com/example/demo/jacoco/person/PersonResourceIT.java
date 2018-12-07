package com.example.demo.jacoco.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.jacoco.JacocoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JacocoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PersonResourceIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    @Sql({
            "/sql/drop_person.sql",
            "/sql/create_person.sql",
            "/sql/populate_person.sql"
    })
    public void gerPersons() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/person"),
                HttpMethod.GET, entity, String.class);

        System.out.println(response.getBody());

        //        String expected = "{id:Course1,name:Spring,description:10 Steps}";
        //
        //        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}