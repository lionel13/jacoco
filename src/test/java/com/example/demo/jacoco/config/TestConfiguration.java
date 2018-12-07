package com.example.demo.jacoco.config;

import org.springframework.context.annotation.Bean;

import com.example.demo.jacoco.person.PersonService;

public class TestConfiguration {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

}
