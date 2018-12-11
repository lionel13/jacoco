package com.example.demo.jacoco.style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/style")
public class StyleResource {

    private StyleService styleService;

    @Autowired
    public StyleResource(StyleService styleService) {
        this.styleService = styleService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<StyleDTO> getPersons() {

        // Récupération des informations
        return styleService.getStyles();
    }
}
