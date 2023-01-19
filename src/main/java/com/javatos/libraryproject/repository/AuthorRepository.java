package com.javatos.libraryproject.repository;

import com.javatos.libraryproject.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author,String> {

}
