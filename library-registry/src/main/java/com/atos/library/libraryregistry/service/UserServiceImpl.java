package com.atos.library.libraryregistry.service;

import com.atos.library.libraryregistry.model.Role;
import com.atos.library.libraryregistry.model.User;
import com.atos.library.libraryregistry.repository.RoleRepository;
import com.atos.library.libraryregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database...", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database...", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
//        System.out.println("Paoooooooooooooooooooooooooooo!!!!!!!!!!!!");
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        System.out.println("Pao1: " + user.toString());
        Role role = roleRepository.findByName(roleName);
        System.out.println("Paoooooooooooooooooooooooooooo!!!!!!!!!!!!");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
