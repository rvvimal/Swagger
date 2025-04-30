package com.school_management.exception;

public class SchoolNotFoundException extends RuntimeException{
    public SchoolNotFoundException(String message){
        super(message);
    }
}
