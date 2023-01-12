package com.javatos.libraryproject.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date birthDate;

    @CPF
    private String cpf;
    private Date registerDate;
    private String phone;
    private String address;
    @Email
    private String email;

}
