package com.example.demo.jacoco.style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {


    private StyleRepository styleRepository;

    private StyleMapper styleMapper;

    @Autowired
    public StyleService(StyleRepository styleRepository, StyleMapper styleMapper) {
        this.styleRepository = styleRepository;
        this.styleMapper = styleMapper;
    }

    public List<StyleDTO> getStyles() {

        List<Style> styleList = styleRepository.findAll();

        return styleList.stream()
                .map(styleMapper::styleToStyleDTO)
                .collect(Collectors.toList());

    }


    public void addStyle(StyleDTO styleDTO) {
        styleRepository.save(styleMapper.styleDTOToStyle(styleDTO));
    }
}
