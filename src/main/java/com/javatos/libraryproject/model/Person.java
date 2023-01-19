package com.javatos.libraryproject.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

@Document
@Data
@SuperBuilder
public abstract class Person implements Serializable {
    @Id
    private String id;
    @NotNull
    private String name;

    private Date birthDate;

    private Date registerDate;

    private String nationality;


}
