package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Author;
import com.atos.library.libraryregistry.repository.AuthorRepository;
import com.atos.library.libraryregistry.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public  AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author findById(String id){
        Optional<Author> author = authorRepository.findById(id);

        return author.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id + ", type: " + Author.class.getName()));
    }

    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    public Author create(Author author){
        return authorRepository.save(author);
    }

    public Author update(Author authorNew){
        Author authorOld = findById(authorNew.getId());
        authorOld.setName(authorNew.getName());
        authorOld.setBirthDate(authorNew.getBirthDate());
        authorOld.setDescription(authorNew.getDescription());
        authorOld.setNationality(authorNew.getNationality());
        return authorRepository.save(authorOld);
    }

    public void delete(String id){
        findById(id);
        authorRepository.deleteById(id);
    }

}
