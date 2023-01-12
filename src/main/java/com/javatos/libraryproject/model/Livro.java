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
    private String nome;
    @ManyToMany
    private List<Author> autores;
    private int paginas;
    @ManyToMany
    private List<Genre> genres;
    private int anoPublicacao;
    private String asin;
    private String resumo;
    @ManyToOne
    private Publisher publisher;
    private String capaLivro;

}
