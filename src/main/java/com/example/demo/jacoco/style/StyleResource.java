package com.example.demo.jacoco.style;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<StyleDTO>> getStyles() {

        // Récupération des informations
        return new ResponseEntity<>(styleService.getStyles(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StyleDTO> getStyle(@PathVariable("id") long styleId) {
        return new ResponseEntity<>(styleService.getStyle(styleId), HttpStatus.OK);
    }

}
