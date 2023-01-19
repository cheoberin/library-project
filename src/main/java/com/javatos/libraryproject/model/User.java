package com.javatos.libraryproject.model;

import lombok.*;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;



public class User extends Person implements Serializable {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;
    @CPF

    private String cpf;
    private String phone;
    private String address;


    protected User(PersonBuilder<?, ?> b) {
        super(b);
    }
}
