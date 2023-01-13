package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Author;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    /*
    public Author findById(long id){
        Optional<Author> author ; //TODO autorRepository.findById(id)
        return author.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        author.class.getName()));
    }

    public List<Author> findAll(){
        //TODO return autorRepository.findAll()
    }

    public Author create(Author author) {
       //TODO setId(null); return autorRepository.save();
    }

    public Author update (long id, Author authorNew){
        Author authorOriginal = findById(id);
        authorOriginal.setBirthDate(authorNew.getBirthDate());
        authorOriginal.setName(authorNew.getName());
        authorOriginal.setDescription(authorNew.getDescription());

        //TODO return autorRepository.save(autorOriginal);
    }

    public void delete(long id){
        findById(id);
        //TODO autorRepository.deleteById(id)
    }

     */

}
