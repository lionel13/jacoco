package com.example.demo.jacoco.style;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jacoco.utils.ControlResourceUtil;

@RestController
@RequestMapping("/api/style")
public class StyleResource {

    private StyleService styleService;

    private final StyleResourceAssembler styleResourceAssembler;

    public StyleResource(StyleService styleService, StyleResourceAssembler styleResourceAssembler) {
        this.styleService = styleService;
        this.styleResourceAssembler = styleResourceAssembler;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resources<Resource<StyleDTO>> getStyles() {

        List<StyleDTO> styles = styleService.getStyles();

        List<Resource<StyleDTO>> style1 = styles.stream()
                .map(styleResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(style1,
                linkTo(methodOn(StyleResource.class).getStyles()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StyleDTO> getStyle(@PathVariable("id") String id) {

        int styleId = ControlResourceUtil.controlId(id);
        return new ResponseEntity<>(styleService.getStyle(styleId), HttpStatus.OK);
    }

}
