package com.javatos.libraryproject.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User extends Person {

    @Email
    private String email;
    private String password;
    private String role;
    @CPF
    private String cpf;
    private String phone;
    private String address;

}
