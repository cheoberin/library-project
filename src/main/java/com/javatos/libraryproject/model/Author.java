package com.javatos.libraryproject.model;

import lombok.Data;

import javax.persistence.Entity;
@Entity
@Data
public class Author extends Person{
    private String description;

}
