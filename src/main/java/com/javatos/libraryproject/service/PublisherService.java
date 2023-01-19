package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Publisher;
import com.javatos.libraryproject.repository.PublisherRepository;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
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



    public Publisher findById(String id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        Publisher.class.getName()));


    }


    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }


    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher update (String id, Publisher publisherNew){
        Publisher publisherOriginal = findById(id);
        publisherOriginal.setName(publisherNew.getName());
        return publisherRepository.save(publisherOriginal);
    }

    public void delete(String id){
        findById(id);
        publisherRepository.deleteById(id);
    }

}
