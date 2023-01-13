package com.javatos.libraryproject.service;

import com.javatos.libraryproject.model.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

   /*
    public Publisher findById(long id){
        Optional<Publisher> publisher ; //TODO autorRepository.findById(id)
        return publisher.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        publisher.class.getName()));


    }


    public List<Publisher> findAll(){
        //TODO return editoraRepository.findAll()
    }


    public Publisher create(Publisher publisher) {
        //TODO setId(null); return editoraRepository.save();
    }

    public Publisher update (long id, Publisher publisherNew){
        Publisher publisherOriginal = findById(id);
        publisherOriginal.setName(publisherNew.getName());
        //TODO return editoraRepository.save(editoraOriginal);
    }

    public void delete(long id){
        findById(id);
        //TODO autorRepository.deleteById(id)
    }
    */
}
