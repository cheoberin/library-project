package com.atos.library.libraryregistry.controller;

import com.atos.library.libraryregistry.model.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atos.library.libraryregistry.service.UserService;
import com.atos.library.libraryregistry.model.User; //domain por model
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>>getUsers() {
        System.out.println("------------------");
        System.out.println("------List--------");
        System.out.println("------------------");
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<User>saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        System.out.println("------------------");
        System.out.println("------user/save--------");
        System.out.println("------------------");
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/role/save").toUriString());
        System.out.println("------------------");
        System.out.println("------role/save--------");
        System.out.println("------------------");
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        System.out.println("------------------");
        System.out.println("------role/addtouser--------");
        System.out.println("------------------");
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
