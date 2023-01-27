package com.atos.library.libraryregistry.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    @Id
    private String id;
    @NotBlank
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
    @NotBlank
    private String asin;
    @NotBlank
    private String summary;
    @NotNull
    @DBRef
    private Publisher publisher;
    private String bookCover;

}
