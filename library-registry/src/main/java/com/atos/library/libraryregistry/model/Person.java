package com.atos.library.libraryregistry.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Document
@Data
@SuperBuilder
public abstract class Person implements Serializable {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private LocalDate registerDate;
    @NotBlank
    private String nationality;

}
