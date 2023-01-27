package com.atos.library.libraryregistry.repository;

import com.atos.library.libraryregistry.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    @Override
    Optional<Author> findById(String s);

    List<Author> findByNationality(String nationality);
}
