package com.example.demo.jacoco.style;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StyleDTO {

    private String id;
    private String nom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        StyleDTO style = (StyleDTO) o;

        return new EqualsBuilder()
                .append(id, style.id)
                .append(nom, style.nom)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(nom)
                .toHashCode();
    }

    @Override public String toString() {
        return ReflectionToStringBuilder.toString(
                this,
                ToStringStyle.MULTI_LINE_STYLE,
                true,
                false
        );
    }
}
