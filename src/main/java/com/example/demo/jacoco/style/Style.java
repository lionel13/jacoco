package com.example.demo.jacoco.style;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_style")
public class Style {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Style style = (Style) o;

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
