package com.javatos.libraryproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany
    private List<Author> authors;
    private int pages;
    @ManyToMany
    private List<Genre> genres;
    private int publicationYear;
    private String asin;
    private String summary;
    @ManyToOne
    private Publisher publisher;
    private String bookCover;

}
