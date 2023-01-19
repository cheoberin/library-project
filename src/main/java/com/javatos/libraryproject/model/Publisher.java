package com.javatos.libraryproject.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Document
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Publisher implements Serializable {

    @Id
    private String id;

    @NotNull
    private String name;


}
