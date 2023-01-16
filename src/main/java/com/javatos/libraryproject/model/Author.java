package com.javatos.libraryproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Author extends Person{
    private String description;
}
