package com.example.demo.jacoco.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public enum CiviliteEnum {
    MADAME("Madame", "Mme"),
    MADEMOISELLE("Mademoiselle", "Melle"),
    MONSIEUR("Monsieur", "M");

    private String name;
    private String abreviation;

    CiviliteEnum(String name, String abreviation){
        this.name = name;
        this.abreviation = abreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(
                this,
                ToStringStyle.MULTI_LINE_STYLE,
                true,
                false
        );
    }
}
