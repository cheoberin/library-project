package com.atos.library.libraryregistry.repository;

import com.atos.library.libraryregistry.model.Role;
//import com.atos.library.libraryregistry.security.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByName(String name);

}
