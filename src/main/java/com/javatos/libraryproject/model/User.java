package com.javatos.libraryproject.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends Person {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role;
    @CPF
    @Column(unique = true)
    private String cpf;
    private String phone;
    private String address;

}
