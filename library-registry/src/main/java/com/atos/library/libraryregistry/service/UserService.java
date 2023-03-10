package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Role;
import com.atos.library.libraryregistry.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

//import io.getarrays.userservice.domain.User;
@Service
public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();


}
