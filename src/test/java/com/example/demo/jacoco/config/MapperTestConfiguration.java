package com.example.demo.jacoco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.jacoco.person.PersonMapper;

/**
 * Created by emilie.deltil on 06/05/2016.
 */
@Configuration
public class MapperTestConfiguration {

    @Bean
    public PersonMapper personMapper() {
        return PersonMapper.INSTANCE;
    }

}
