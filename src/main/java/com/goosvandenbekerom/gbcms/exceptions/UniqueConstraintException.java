package com.goosvandenbekerom.gbcms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConstraintException extends RuntimeException {
    public UniqueConstraintException(Class type) {
        super(String.format(
                "Something went wrong, please make sure that the %s you are trying to create doesn't already exist",
                type.getSimpleName()));
    }
}
