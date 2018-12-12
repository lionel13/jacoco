package com.example.demo.jacoco.style;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public StyleDTO getStyle(long styleId) {
        return styleRepository.findById(styleId).map(
                styleMapper::styleToStyleDTO)
                .orElseThrow(() -> new StyleNotFoundException(styleId));
    }
}
