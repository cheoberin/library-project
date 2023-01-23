package com.atos.library.libraryregistry.repository;

import com.atos.library.libraryregistry.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher, String> {
}
