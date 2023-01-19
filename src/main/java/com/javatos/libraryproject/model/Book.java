package com.javatos.libraryproject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    @DBRef
    @ToString.Exclude
    private List<Author> authors;
    @NotNull
    private int pages;
    @NotNull
    @DBRef
    @ToString.Exclude
    private List<Genre> genres;
    @NotNull
    private int publicationYear;
    @NotNull
    private String asin;
    @NotNull
    private String summary;
    @NotNull
    @DBRef
    private Publisher publisher;

    private String bookCover;


}
