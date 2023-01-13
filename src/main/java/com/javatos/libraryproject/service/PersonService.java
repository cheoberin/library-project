package com.javatos.libraryproject.service;


import com.javatos.libraryproject.model.Person;
import com.javatos.libraryproject.resources.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    /*
    public Person findById(long id){
        Optional<Person> person ; //TODO genreRepository.findById(id)
        return person.orElseThrow(
                () -> new ObjectNotFoundException("Object not Found: " + id + " , type: " +
                        person.class.getName()));

    }


    public List<Person> findAll(){
        //TODO return genreRepository.findAll()
    }


    public Person create(Person person) {
        //TODO setId(null); return genreRepository.save();
    }

    public Person update (long id, Person personNew){
        Person personOriginal = findById(id);
        personOriginal.setAddress(personNew.getAddress());
        personOriginal.setBirthDate(personNew.getBirthDate());
        personOriginal.setCpf(personNew.getCpf());
        personOriginal.setEmail(personNew.getEmail());
        personOriginal.setPhone(personNew.getPhone());
        personOriginal.setRegisterDate(personNew.getRegisterDate());
        personOriginal.setName(personNew.getName());
        //TODO return genreRepository.save(genreOriginal);
    }

    public void delete(long id){
        findById(id);
        //TODO genreRepository.deleteById(id)
    }

     */
}
