package com.goosvandenbekerom.gbcms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class entityType, Object identifier) {
        super(String.format("%s with identifier %s was not found", entityType.getSimpleName(), identifier));
    }
}
