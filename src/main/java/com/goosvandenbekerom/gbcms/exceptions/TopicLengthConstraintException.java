package com.goosvandenbekerom.gbcms.exceptions;

import com.goosvandenbekerom.gbcms.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TopicLengthConstraintException extends RuntimeException {
    public TopicLengthConstraintException() {
        super(String.format("Something went wrong, the name of a topic has a maximum of %s characters", Constants.MAX_TOPIC_LENGTH));
    }
}
