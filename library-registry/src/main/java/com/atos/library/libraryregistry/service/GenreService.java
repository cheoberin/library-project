package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.repository.GenreRepository;
import com.atos.library.libraryregistry.resources.exceptions.ObjectNotFoundException;
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

    public Genre findById(String id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id + ", type: " + Genre.class.getName()));
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Genre genreNew) {
        Genre genreOld = findById(genreNew.getId());
        genreOld.setName(genreNew.getName());
        return genreRepository.save(genreOld);
    }

    public void delete(String id) {
        findById(id);
        genreRepository.deleteById(id);
    }

}
