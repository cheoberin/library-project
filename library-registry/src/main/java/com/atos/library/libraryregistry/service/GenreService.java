package com.atos.library.libraryregistry.service;



import com.atos.library.libraryregistry.model.Genre;
import com.atos.library.libraryregistry.repository.GenreRepository;
import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
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

    public Genre findById(String id){
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Genre.class.getName()));

    }


    public List<Genre> findAll(){
        return genreRepository.findAll();
    }


    public Genre create(Genre genre) {
        try {
            return genreRepository.save(genre);
        }catch(Exception ex){
            throw new DataViolationException("Error creating :" + Genre.class.getName());
        }
    }

    public Genre update (Genre genreNew){
        Genre genreOriginal = findById(genreNew.getId());
        genreOriginal.setName(genreNew.getName());
        return genreRepository.save(genreOriginal);
    }

    public void delete(String id){
        findById(id);
        genreRepository.deleteById(id);
    }
}
