package com.atos.library.libraryregistry.service;


import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.repository.AuthorRepository;
import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

     private final AuthorRepository authorRepository;

    @Autowired   //https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Author findById(String id){
        Optional<Author> author = authorRepository.findById(id);

        return author.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Author.class.getName()));
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public List<Author> findByNationality(String nationality) {
        return authorRepository.findByNationality(nationality);
    }

    public Author create(Author author) {
        try {
          return authorRepository.save(author);
        }catch(Exception ex){
            throw new DataViolationException("Error creating :" + Author.class.getName());
        }
    }

    public Author update (Author authorNew){
        Author authorOriginal = findById(authorNew.getId());
        authorOriginal.setName(authorNew.getName());
        authorOriginal.setNationality(authorNew.getNationality());
        authorOriginal.setDescription(authorNew.getDescription());
        return authorRepository.save(authorOriginal);
    }

    public void delete(String id){
        findById(id);
        authorRepository.deleteById(id);
    }
}
