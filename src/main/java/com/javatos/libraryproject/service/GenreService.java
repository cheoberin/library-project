package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Genre;
import com.javatos.libraryproject.repository.GenreRepository;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findById(long id){
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Genre.class.getName()));

    }


    public List<Genre> findAll(){
        return genreRepository.findAll();
    }


    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update (long id, Genre genreNew){
        Genre genreOriginal = findById(id);
        genreOriginal.setName(genreNew.getName());
        return genreRepository.save(genreOriginal);
    }

    public void delete(long id){
        findById(id);
        genreRepository.deleteById(id);
    }
}
