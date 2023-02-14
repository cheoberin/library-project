package com.atos.library.libraryregistry.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {
    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;

}
