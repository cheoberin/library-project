package com.atos.library.libraryregistry.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class User extends Person implements Serializable {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
    @CPF
    private String cpf;
    private String phone;
    private String address;

    protected User(PersonBuilder<?, ?> b){
        super(b);
    }

}
