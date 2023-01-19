package com.javatos.libraryproject.repository;

import com.javatos.libraryproject.model.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends MongoRepository<Publisher,String> {

}
