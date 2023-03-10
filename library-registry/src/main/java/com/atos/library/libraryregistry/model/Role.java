package com.atos.library.libraryregistry.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;



}
