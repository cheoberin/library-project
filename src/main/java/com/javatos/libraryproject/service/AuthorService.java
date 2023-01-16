package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Author;
import com.javatos.libraryproject.repository.AuthorRepository;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
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


    public Author findById(long id){
        Optional<Author> author = authorRepository.findById(id);

        return author.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Author.class.getName()));
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author update (long id, Author authorNew){
        Author authorOriginal = findById(id);
        authorOriginal.setName(authorNew.getName());
        authorOriginal.setNationality(authorNew.getNationality());
        authorOriginal.setDescription(authorNew.getDescription());
        return authorRepository.save(authorOriginal);
    }

    public void delete(long id){
        findById(id);
        authorRepository.deleteById(id);
    }

}
