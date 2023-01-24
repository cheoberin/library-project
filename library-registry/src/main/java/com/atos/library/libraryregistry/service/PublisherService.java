package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Publisher;
import com.atos.library.libraryregistry.repository.PublisherRepository;
import com.atos.library.libraryregistry.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher findById(String id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id + ", type: " + Publisher.class.getName()));
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher update(Publisher publisherNew) {
        Publisher publisherOld = findById(publisherNew.getId());
        publisherOld.setName(publisherNew.getName());
        return publisherRepository.save(publisherOld);
    }

    public void delete(String id) {
        findById(id);
        publisherRepository.deleteById(id);
    }

}
