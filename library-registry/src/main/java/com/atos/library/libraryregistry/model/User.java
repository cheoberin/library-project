package com.atos.library.libraryregistry.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String Id;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
//    @ManyToMany(fetch = FetchType.EAGER)
    @DBRef
    private Collection<Role> roles = new ArrayList<>();



//
//
//    @Email
//    @NotBlank
//    private String email;
//    @NotBlank
//    private String password;
//    @NotNull
//    private Role role;
//    @CPF
//    private String cpf;
//    private String phone;
//    private String address;
//
//    protected User(PersonBuilder<?, ?> b){
//        super(b);
//    }

}
