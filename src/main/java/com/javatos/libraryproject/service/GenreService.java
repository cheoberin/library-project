package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Genre;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    /*
    public Genre findById(long id){
        Optional<Genre> genre ; //TODO genreRepository.findById(id)
        return genre.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        genre.class.getName()));

    }


    public List<Genre> findAll(){
        //TODO return genreRepository.findAll()
    }


    public Genre create(Genre genre) {
        //TODO setId(null); return genreRepository.save();
    }

    public Genre update (long id, Genre genreNew){
        Genre genreOriginal = findById(id);
        genreOriginal.setName(genreNew.getName());
        //TODO return genreRepository.save(genreOriginal);
    }

    public void delete(long id){
        findById(id);
        //TODO genreRepository.deleteById(id)
    }

     */
}
