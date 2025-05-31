package ru.rsreu.gorobchenko.polyclinicweb.dao.core;

public class DbTypeException extends RuntimeException {

    public DbTypeException() {
        super();
    }

    public DbTypeException(String message) {
        super(message);
    }
}
