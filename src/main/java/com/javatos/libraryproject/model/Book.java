package com.javatos.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotNull
    @ManyToMany
    @Valid
    private List<Author> authors;
    @NotNull
    private int pages;
    @NotNull
    @ManyToMany
    @Valid
    private List<Genre> genres;
    @NotNull
    private int publicationYear;
    @NotBlank
    @Column(unique = true)
    private String asin;
    @NotBlank
    private String summary;
    @NotNull
    @ManyToOne
    @Valid
    private Publisher publisher;
    private String bookCover;

}
