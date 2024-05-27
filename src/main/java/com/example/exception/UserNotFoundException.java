package com.example.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {

    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
