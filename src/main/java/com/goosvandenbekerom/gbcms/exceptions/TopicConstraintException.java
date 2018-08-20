package com.goosvandenbekerom.gbcms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TopicConstraintException extends RuntimeException {
    public TopicConstraintException() {
        super("Please make sure that the topic you are trying to create/edit doesn't already exist and has less then 30 characters");
    }
}
