package com.example.demo.jacoco.person;

import com.example.demo.jacoco.domain.CiviliteEnum;
import com.example.demo.jacoco.model.AuditableDTO;
import com.example.demo.jacoco.utils.validator.Date;
import com.example.demo.jacoco.utils.validator.Enum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonDTO extends AuditableDTO {

    private String id;

    @Size(max = 32)
    @NotEmpty
    private String nom;
    @Size(max = 32)
    @NotEmpty
    private String prenom;
    @Enum(enumClass = CiviliteEnum.class)
    private String civilite;
    @Email
    private String mail;
    @Pattern(regexp = "^[1-9]\\d*$", message = "L'age doit être un nombre positif")
    private String age;
    @Date
    private String birthday;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonDTO personDTO = (PersonDTO) o;

        return new EqualsBuilder()
                .append(nom, personDTO.nom)
                .append(prenom, personDTO.prenom)
                .append(civilite, personDTO.civilite)
                .append(mail, personDTO.mail)
                .append(age, personDTO.age)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(nom)
                .append(prenom)
                .append(civilite)
                .append(mail)
                .append(age)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(
                this,
                ToStringStyle.MULTI_LINE_STYLE,
                true,
                false
        );
    }

}
