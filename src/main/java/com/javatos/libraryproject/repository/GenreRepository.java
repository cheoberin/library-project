package com.javatos.libraryproject.repository;

import com.javatos.libraryproject.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre,String> {

}
