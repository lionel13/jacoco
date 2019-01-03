package com.example.demo.jacoco.style;

import org.mapstruct.Mapper;

@Mapper
public interface StyleMapper {
    StyleDTO styleToStyleDTO(Style style);

    Style styleDTOToStyle(StyleDTO styleDTO);
}
