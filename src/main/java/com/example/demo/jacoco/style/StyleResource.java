package com.example.demo.jacoco.style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addStyle(@Valid @RequestBody StyleDTO styleDTO) {
        styleService.addStyle(styleDTO);
    }


}
