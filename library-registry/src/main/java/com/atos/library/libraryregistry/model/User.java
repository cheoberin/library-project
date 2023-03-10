package com.atos.library.libraryregistry.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String _id;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    @DBRef
    private Collection<Role> roles = new ArrayList<>();

}
