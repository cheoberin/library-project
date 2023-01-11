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
    private List<Autor> autores;
    private int paginas;
    @ManyToMany
    private List<Genero> generos;
    private int anoPublicacao;
    private String asin;
    private String resumo;
    @ManyToOne
    private Editora editora;
    private String capaLivro;

}
