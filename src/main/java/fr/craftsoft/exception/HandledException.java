package fr.craftsoft.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HandledException extends GenericException {

    public HandledException(String message) {
        super(message);
    }
}
