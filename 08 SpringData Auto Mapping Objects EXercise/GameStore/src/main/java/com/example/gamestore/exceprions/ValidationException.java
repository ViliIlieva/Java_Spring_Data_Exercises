package com.example.gamestore.exceprions;

public class ValidationException extends RuntimeException{

    public ValidationException(String reason){
        super(reason);
    }
}
