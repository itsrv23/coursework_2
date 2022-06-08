package ru.itsrv23.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundLimitQuestionsException extends RuntimeException{
    public NotFoundLimitQuestionsException(String message) {
        super(message);
    }
}
