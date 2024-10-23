package com.fs.sistemadenotas.exception;

public class StudentAlreadyEnrolledException extends RuntimeException {

    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}
