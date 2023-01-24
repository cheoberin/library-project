package com.atos.library.libraryregistry.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class Author extends Person implements Serializable {

    private String description;

}
