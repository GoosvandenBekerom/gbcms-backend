package com.goosvandenbekerom.gbcms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Class type, Object identifier) {
        super(String.format("%s with identifier %s already exists", type.getSimpleName(), identifier));
    }
}
