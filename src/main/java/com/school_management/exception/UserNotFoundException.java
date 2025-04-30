package com.school_management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message){
        super(message);
    }
}
