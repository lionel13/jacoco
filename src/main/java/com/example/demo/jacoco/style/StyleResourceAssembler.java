package com.example.demo.jacoco.style;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class StyleResourceAssembler implements ResourceAssembler<StyleDTO, Resource<StyleDTO>> {

    @Override
    public Resource<StyleDTO> toResource(StyleDTO styleDTO) {
        return new Resource<>(styleDTO,
                linkTo(methodOn(StyleResource.class).getStyle(styleDTO.getId())).withSelfRel(),
                linkTo(methodOn(StyleResource.class).getStyles()).withRel("persons"));
    }
}
