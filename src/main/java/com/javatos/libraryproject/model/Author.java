package com.javatos.libraryproject.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;


@Data
@SuperBuilder
public class Author extends Person implements Serializable {

    private String description;



}
