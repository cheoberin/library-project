package com.javatos.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    @ManyToMany
    private List<Author> authors;
    @NotNull
    private int pages;
    @NotNull
    @ManyToMany
    private List<Genre> genres;
    @NotNull
    private int publicationYear;
    @NotNull
    @Column(unique = true)
    private String asin;
    @NotNull
    private String summary;
    @NotNull
    @ManyToOne
    private Publisher publisher;
    private String bookCover;

}
