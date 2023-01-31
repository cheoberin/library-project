package com.atos.library.libraryregistry.repository;

import com.atos.library.libraryregistry.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
    Page<Genre> findAllByName (Pageable pageable);
}
